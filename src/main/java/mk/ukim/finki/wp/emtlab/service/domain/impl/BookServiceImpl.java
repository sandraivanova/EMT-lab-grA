package mk.ukim.finki.wp.emtlab.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.emtlab.events.BookRentedEvent;
import mk.ukim.finki.wp.emtlab.model.domain.Book;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.enums.State;
import mk.ukim.finki.wp.emtlab.model.projection.BookSummaryProjection;
import mk.ukim.finki.wp.emtlab.repository.BookRepository;
import mk.ukim.finki.wp.emtlab.service.domain.BookHistoryService;
import mk.ukim.finki.wp.emtlab.service.domain.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    BookHistoryService bookHistoryService;
    private final BookRepository bookRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public BookServiceImpl(BookRepository bookRepository, ApplicationEventPublisher applicationEventPublisher, BookHistoryService bookHistoryService) {
        this.bookRepository = bookRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.bookHistoryService=bookHistoryService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAllByDeletedFalse();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public Book create(Book book) {
        Book savedBook = bookRepository.save(book);
        bookHistoryService.recordAdd(savedBook.getId());
        return savedBook;    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setName(book.getName());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setState(book.getState());
                    existingBook.setCategory(book.getCategory());
                    existingBook.setAvailableCopies(book.getAvailableCopies());

                    Book updatedBook = bookRepository.save(existingBook);
                    bookHistoryService.recordUpdate(updatedBook.getId());
                    return updatedBook;
                });
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    if (book.getState() != State.BAD) {
                        throw new IllegalStateException("Only books in BAD condition can be deleted.");
                    }

                    book.setDeleted(true);
                    Book deletedBook = bookRepository.save(book);
                    bookHistoryService.recordDelete(deletedBook.getId());
                    return deletedBook;
                });
    }

    @Transactional
    @Override
    public Optional<Book> rent(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    if (book.getState() != State.GOOD) {
                        throw new IllegalStateException("Only books in GOOD condition can be rented.");
                    }

                    if (book.getAvailableCopies() <= 0) {
                        throw new IllegalStateException("No available copies for this book.");
                    }

                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    Book savedBook = bookRepository.save(book);

                    applicationEventPublisher.publishEvent(
                            new BookRentedEvent(
                                    savedBook.getId(),
                                    savedBook.getName(),
                                    savedBook.getAvailableCopies()
                            )
                    );

                    return savedBook;
                });
    }

    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findAllByDeletedFalseAndCategory(category);
    }

    @Override
    public Page<Book> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAllByDeletedFalse(pageable);
    }

    @Override
    public Page<Book> filter(Category category,
                             State state,
                             Long authorId,
                             Boolean available,
                             int page,
                             int size,
                             String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.filterBooks(category, state, authorId, available, pageable);
    }

    @Override
    public Page<BookSummaryProjection> findAllSummary(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAllProjectedBy(pageable);
    }

    @Override
    public List<Book> findAllWithAuthorAndCountry() {
        return bookRepository.findAllWithAuthorAndCountry();
    }


}

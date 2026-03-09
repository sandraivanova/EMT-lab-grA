package mk.ukim.finki.wp.emtlab.service.domain.impl;

import mk.ukim.finki.wp.emtlab.model.domain.Book;
import mk.ukim.finki.wp.emtlab.model.enums.State;
import mk.ukim.finki.wp.emtlab.repository.BookRepository;
import mk.ukim.finki.wp.emtlab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setName(book.getName());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setState(book.getState());
                    existingBook.setCategory(book.getCategory());
                    existingBook.setAvailableCopies(book.getAvailableCopies());
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    if (book.getState() != State.BAD) {
                        throw new IllegalStateException("Only books in BAD condition can be deleted.");
                    }
                    bookRepository.delete(book);
                    return book;
                })
                ;
    }

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
                    return bookRepository.save(book);
                });    }
}

package mk.ukim.finki.wp.emtlab.service.domain.impl;

import mk.ukim.finki.wp.emtlab.model.domain.BookHistory;
import mk.ukim.finki.wp.emtlab.repository.BookHistoryRepository;
import mk.ukim.finki.wp.emtlab.service.domain.BookHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookHistoryServiceImpl implements BookHistoryService {
    private final BookHistoryRepository bookHistoryRepository;

    public BookHistoryServiceImpl(BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }


    @Override
    public BookHistory findByBookId(Long bookId) {
        return bookHistoryRepository.findByBookId(bookId)
                .orElseGet(() -> bookHistoryRepository.save(new BookHistory(bookId)));    }

    @Override
    public void recordAdd(Long bookId) {
        BookHistory history = findByBookId(bookId);
        history.addDescription("Added book with id " + bookId + " at " + LocalDateTime.now());
        bookHistoryRepository.save(history);
    }

    @Override
    public void recordUpdate(Long bookId) {
        BookHistory history = findByBookId(bookId);
        history.addDescription("Updated book with id " + bookId + " at " + LocalDateTime.now());
        bookHistoryRepository.save(history);
    }

    @Override
    public void recordDelete(Long bookId) {
        BookHistory history = findByBookId(bookId);
        history.addDescription("Deleted book with id " + bookId + " at " + LocalDateTime.now());
        bookHistoryRepository.save(history);
    }

    @Override
    public void recordRent(Long bookId) {
        BookHistory history = findByBookId(bookId);
        history.addDescription("Rented book with id " + bookId + " at " + LocalDateTime.now());
        bookHistoryRepository.save(history);
    }
}

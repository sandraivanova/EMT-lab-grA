package mk.ukim.finki.wp.emtlab.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.emtlab.events.BookRentedEvent;
import mk.ukim.finki.wp.emtlab.model.domain.BookActivity;
import mk.ukim.finki.wp.emtlab.repository.BookActivityRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookRentedEventListener {

    private final BookActivityRepository activityLogRepository;

    //bez ova ne mi gi dodavase odma sega iznajmenite knigi
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleBookBorrowed(BookRentedEvent event) {
        log.info("Book borrowed: {}", event.bookName());

        BookActivity borrowedLog = new BookActivity();
        borrowedLog.setBookName(event.bookName());
        borrowedLog.setEventType("BORROWED");
        borrowedLog.setEventTime(LocalDateTime.now());
        activityLogRepository.save(borrowedLog);
        log.info("Saved BORROWED activity!");


        if (event.availableCopies() == 0) {
            log.warn("Book '{}' is now out of stock!", event.bookName());

            BookActivity unavailableLog = new BookActivity();
            unavailableLog.setBookName(event.bookName());
            unavailableLog.setEventType("UNAVAILABLE");
            unavailableLog.setEventTime(LocalDateTime.now());
            activityLogRepository.save(unavailableLog);
        }
    }
}
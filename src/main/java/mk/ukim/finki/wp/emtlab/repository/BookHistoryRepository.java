package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.model.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
    Optional<BookHistory> findByBookId(Long bookId);
}

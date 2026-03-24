package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.model.domain.BookActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookActivityRepository extends JpaRepository<BookActivity, Long> {
}

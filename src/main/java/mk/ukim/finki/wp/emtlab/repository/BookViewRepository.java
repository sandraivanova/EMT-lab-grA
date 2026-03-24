package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.model.views.BookView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookViewRepository extends JpaRepository<BookView, Long> {
}

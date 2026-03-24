package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.model.views.BookCategoryStatisticsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookCategoryStatisticsRepository extends JpaRepository<BookCategoryStatisticsView,String> {
    @Modifying
    @Query(value = "CALL refresh_books_statistics_mv()", nativeQuery = true)
    void refresh();
}

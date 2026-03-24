package mk.ukim.finki.wp.emtlab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "books_statistics_mv")
@Immutable
public class BookCategoryStatisticsView {
    @Id
    private String category;

    @Column(name = "total_books")
    private Long totalBooks;

    @Column(name = "total_available_copies")
    private Long totalAvailableCopies;

    @Column(name = "books_not_in_good_condition")
    private Long booksNotInGoodCondition;

    public String getCategory() {
        return category;
    }

    public Long getTotalBooks() {
        return totalBooks;
    }

    public Long getTotalAvailableCopies() {
        return totalAvailableCopies;
    }

    public Long getBooksNotInGoodCondition() {
        return booksNotInGoodCondition;
    }
}

package mk.ukim.finki.wp.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_history")
@Data
@NoArgsConstructor
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false, unique = true)
    private Long bookId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_history_descriptions", joinColumns = @JoinColumn(name = "book_history_id"))
    @Column(name = "description", nullable = false)
    private List<String> descriptions = new ArrayList<>();

    public BookHistory(Long bookId) {
        this.bookId = bookId;
        this.descriptions = new ArrayList<>();
    }

    public void addDescription(String description) {
        this.descriptions.add(description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}

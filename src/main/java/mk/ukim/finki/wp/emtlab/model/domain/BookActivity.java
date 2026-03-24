package mk.ukim.finki.wp.emtlab.model.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_activity")
public class BookActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "event_time", nullable = false)
    private LocalDateTime eventTime;

    @Column(name = "event_type", nullable = false)
    private String eventType;


    public BookActivity() {
    }

    public BookActivity(String bookName, LocalDateTime eventTime, String eventType) {
        this.bookName = bookName;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public String getEventType() {
        return eventType;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

}
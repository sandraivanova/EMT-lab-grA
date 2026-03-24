package mk.ukim.finki.wp.emtlab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name="book_view")
@Immutable
public class BookView {

    @Id
    private Long id;

    private String name;

    private String category; // string view nema enum

    private String state;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "author_full_name")
    private String authorFullName;

    @Column(name = "country_name")
    private String countryName;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getState() { return state; }
    public int getAvailableCopies() { return availableCopies; }
    public String getAuthorFullName() { return authorFullName; }
    public String getCountryName() { return countryName; }


}

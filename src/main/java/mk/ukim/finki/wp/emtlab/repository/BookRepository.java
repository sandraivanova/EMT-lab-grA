package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.model.domain.Book;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.enums.State;
import mk.ukim.finki.wp.emtlab.model.projection.BookDetailsProjection;
import mk.ukim.finki.wp.emtlab.model.projection.BookSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findAllByDeletedFalse();

    Optional<Book> findByIdAndDeletedFalse(Long id);

    List<Book> findAllByDeletedFalseAndCategory(Category category);

    Page<Book> findAllByDeletedFalse(Pageable pageable);

    @Query("""
        select b from Book b
        where b.deleted = false
        and (:category is null or b.category = :category)
        and (:state is null or b.state = :state)
        and (:authorId is null or b.author.id = :authorId)
        and (
             :available is null
             or (:available = true and b.availableCopies > 0)
             or (:available = false and b.availableCopies = 0)
        )
    """)
    Page<Book> filterBooks(
            @Param("category") Category category,
            @Param("state") State state,
            @Param("authorId") Long authorId,
            @Param("available") Boolean available,
            Pageable pageable
    );

    @Query("""
    select b.id as id,
           b.name as name,
           b.category as category,
           b.state as state,
           b.availableCopies as availableCopies
    from Book b
    where b.deleted = false
""")
    Page<BookSummaryProjection> findAllProjectedBy(Pageable pageable);

    @Query("""
    select b.id as id,
           b.name as name,
           b.category as category,
           b.state as state,
           b.availableCopies as availableCopies,
           concat(a.name, ' ', a.surname) as authorName,
           c.name as countryName
    from Book b
    join b.author a
    join a.country c
    where b.deleted = false
""")
    List<BookDetailsProjection> findAllDetailedProjected();

    @EntityGraph(value = "book-author-country-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select b from Book b where b.deleted = false")
    List<Book> findAllWithAuthorAndCountry();
}



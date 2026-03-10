package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.model.domain.Book;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByDeletedFalse();

    Optional<Book> findByIdAndDeletedFalse(Long id);

    List<Book> findAllByDeletedFalseAndCategory(Category category);

}

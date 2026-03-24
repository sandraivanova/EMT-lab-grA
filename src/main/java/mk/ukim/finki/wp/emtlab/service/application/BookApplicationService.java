package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.enums.State;
import mk.ukim.finki.wp.emtlab.model.projection.BookSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();

    Optional<DisplayBookDto> findById(Long id);

    DisplayBookDto create(CreateBookDto createBookDTO);

    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDTO);

    Optional<DisplayBookDto> deleteById(Long id);

    Optional<DisplayBookDto> rent(Long id);

    List<DisplayBookDto> findByCategory(Category category);

    Page<DisplayBookDto> findAll(int page, int size, String sortBy);

    Page<DisplayBookDto> filter(Category category,
                                State state,
                                Long authorId,
                                Boolean available,
                                int page,
                                int size,
                                String sortBy);

    Page<BookSummaryProjection> findAllSummary(int page, int size, String sortBy);

    List<DisplayBookDto> findAllWithAuthorAndCountry();

}

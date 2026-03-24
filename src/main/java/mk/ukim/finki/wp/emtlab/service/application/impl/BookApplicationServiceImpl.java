package mk.ukim.finki.wp.emtlab.service.application.impl;

import mk.ukim.finki.wp.emtlab.model.domain.Author;
import mk.ukim.finki.wp.emtlab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.enums.State;
import mk.ukim.finki.wp.emtlab.model.projection.BookSummaryProjection;
import mk.ukim.finki.wp.emtlab.service.application.BookApplicationService;
import mk.ukim.finki.wp.emtlab.service.domain.AuthorService;
import mk.ukim.finki.wp.emtlab.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public DisplayBookDto create(CreateBookDto createBookDTO) {
        Author author = authorService.findById(createBookDTO.authorId())
                .orElseThrow(() -> new IllegalStateException(
                        "Author not found with id: " + createBookDTO.authorId()
                ));

        return DisplayBookDto.from(
                bookService.create(createBookDTO.toBook(author))
        );
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDTO) {
        Author author = authorService.findById(createBookDTO.authorId())
                .orElseThrow(() -> new IllegalStateException(
                        "Author not found with id: " + createBookDTO.authorId()
                ));

        return bookService.update(id, createBookDTO.toBook(author))
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService.deleteById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> rent(Long id) {
        return bookService.rent(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findByCategory(Category category) {
        return DisplayBookDto.from(bookService.findByCategory(category));
    }

    @Override
    public Page<DisplayBookDto> findAll(int page, int size, String sortBy) {
        return bookService.findAll(page, size, sortBy)
                .map(DisplayBookDto::from);
    }

    @Override
    public Page<DisplayBookDto> filter(Category category,
                                       State state,
                                       Long authorId,
                                       Boolean available,
                                       int page,
                                       int size,
                                       String sortBy) {
        return bookService.filter(category, state, authorId, available, page, size, sortBy)
                .map(DisplayBookDto::from);
    }

    @Override
    public Page<BookSummaryProjection> findAllSummary(int page, int size, String sortBy) {
        return bookService.findAllSummary(page, size, sortBy);
    }

    @Override
    public List<DisplayBookDto> findAllWithAuthorAndCountry() {
        return DisplayBookDto.from(bookService.findAllWithAuthorAndCountry());
    }


}

package mk.ukim.finki.wp.emtlab.service.application.impl;

import mk.ukim.finki.wp.emtlab.model.domain.Author;
import mk.ukim.finki.wp.emtlab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.emtlab.service.application.BookApplicationService;
import mk.ukim.finki.wp.emtlab.service.domain.AuthorService;
import mk.ukim.finki.wp.emtlab.service.domain.BookService;
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
        );    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDTO) {
        Author author = authorService.findById(createBookDTO.authorId())
                .orElseThrow(() -> new IllegalStateException(
                        "Author not found with id: " + createBookDTO.authorId()
                ));

        return bookService.update(id, createBookDTO.toBook(author))
                .map(DisplayBookDto::from);    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService.deleteById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> rent(Long id) {
        return bookService.rent(id).map(DisplayBookDto::from);
    }
}

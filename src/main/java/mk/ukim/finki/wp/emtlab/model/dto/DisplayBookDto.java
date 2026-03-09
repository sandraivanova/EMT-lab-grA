package mk.ukim.finki.wp.emtlab.model.dto;

import mk.ukim.finki.wp.emtlab.model.domain.Book;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.enums.State;

import java.util.List;

public record DisplayBookDto(
        Long id,
        String name,
        Category category,
        String authorName,
        State state,
        Integer avaliableCopies

) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getName() + " " + book.getAuthor().getSurname(),
                book.getState(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream()
                .map(DisplayBookDto::from)
                .toList();
    }

}

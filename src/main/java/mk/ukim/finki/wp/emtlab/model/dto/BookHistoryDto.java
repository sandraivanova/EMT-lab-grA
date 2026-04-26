package mk.ukim.finki.wp.emtlab.model.dto;

import mk.ukim.finki.wp.emtlab.model.domain.BookHistory;

import java.util.List;

public record BookHistoryDto(
        Long bookId,
        List<String> descriptions
) {
    public static BookHistoryDto from(BookHistory bookHistory) {
        return new BookHistoryDto(
                bookHistory.getBookId(),
                bookHistory.getDescriptions()
        );
    }
}

package mk.ukim.finki.wp.emtlab.web.controller;

import mk.ukim.finki.wp.emtlab.model.dto.BookHistoryDto;
import mk.ukim.finki.wp.emtlab.service.domain.BookHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book-history")
public class BookHistoryController {

    private final BookHistoryService bookHistoryService;

    public BookHistoryController(BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookHistoryDto> getHistoryByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(
                BookHistoryDto.from(bookHistoryService.findByBookId(bookId))
        );
    }
}
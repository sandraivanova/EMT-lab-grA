package mk.ukim.finki.wp.emtlab.web.controller;

import mk.ukim.finki.wp.emtlab.model.views.BookView;
import mk.ukim.finki.wp.emtlab.service.domain.BookViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book-view")
public class BookViewController {
    private final BookViewService bookViewService;

    public BookViewController(BookViewService bookViewService) {
        this.bookViewService = bookViewService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<BookView>> getBooksFromView() {
        return ResponseEntity.ok(bookViewService.findAll());
    }
}

package mk.ukim.finki.wp.emtlab.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.wp.emtlab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayBookDto;
import mk.ukim.finki.wp.emtlab.service.application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll() {
        return ResponseEntity.ok(bookApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> create(@RequestBody @Valid CreateBookDto createBookDTO) {
        return ResponseEntity.ok(bookApplicationService.create(createBookDTO));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayBookDto> update(
            @PathVariable Long id,
            @RequestBody @Valid CreateBookDto createBookDTO
    ) {
        return bookApplicationService.update(id, createBookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayBookDto> deleteById(@PathVariable Long id) {
        return bookApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/rent")
    public ResponseEntity<DisplayBookDto> rent(@PathVariable Long id) {
        return bookApplicationService.rent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


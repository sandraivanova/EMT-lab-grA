package mk.ukim.finki.wp.emtlab.web.controller;

import mk.ukim.finki.wp.emtlab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.emtlab.service.domain.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayAuthorDto>> findAll() {
        return ResponseEntity.ok(
                authorService.findAll().stream()
                        .map(DisplayAuthorDto::from)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorService.findById(id)
                .map(author -> ResponseEntity.ok(DisplayAuthorDto.from(author)))
                .orElse(ResponseEntity.notFound().build());
    }
}

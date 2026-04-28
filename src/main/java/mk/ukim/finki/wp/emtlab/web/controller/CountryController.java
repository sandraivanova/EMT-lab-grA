package mk.ukim.finki.wp.emtlab.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.wp.emtlab.model.dto.CreateCountryDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayCountryDto;
import mk.ukim.finki.wp.emtlab.service.domain.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<DisplayCountryDto>> findAll() {
        return ResponseEntity.ok(
                countryService.findAll().stream()
                        .map(DisplayCountryDto::from)
                        .toList()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(country -> ResponseEntity.ok(DisplayCountryDto.from(country)))
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> create(
            @RequestBody @Valid CreateCountryDto dto
    ) {
        return ResponseEntity.ok(
                DisplayCountryDto.from(
                        countryService.create(dto.toCountry())
                )
        );
    }
}
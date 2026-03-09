package mk.ukim.finki.wp.emtlab.service.application.impl;

import mk.ukim.finki.wp.emtlab.model.domain.Country;
import mk.ukim.finki.wp.emtlab.model.dto.CreateAuthorDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.emtlab.service.application.AuthorApplicationService;
import mk.ukim.finki.wp.emtlab.service.domain.AuthorService;
import mk.ukim.finki.wp.emtlab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return authorService.findAll()
                .stream().
                map(DisplayAuthorDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public DisplayAuthorDto create(CreateAuthorDto createAuthorDTO) {
        Country country = countryService.findById(createAuthorDTO.countryId())
                .orElseThrow(() -> new IllegalStateException(
                        "Country not found with id: " + createAuthorDTO.countryId()
                ));

        return DisplayAuthorDto.from(
                authorService.create(createAuthorDTO.toAuthor(country))
        );
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDTO) {
        Country country = countryService.findById(createAuthorDTO.countryId())
                .orElseThrow(() -> new IllegalStateException(
                        "Country not found with id: " + createAuthorDTO.countryId()
                ));

        return authorService.update(id, createAuthorDTO.toAuthor(country))
                .map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> deleteById(Long id) {
        return authorService.deleteById(id).map(DisplayAuthorDto::from);
    }
}

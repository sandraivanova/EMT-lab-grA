package mk.ukim.finki.wp.emtlab.service.application.impl;

import mk.ukim.finki.wp.emtlab.model.dto.CreateCountryDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayCountryDto;
import mk.ukim.finki.wp.emtlab.service.application.CountryApplicationService;
import mk.ukim.finki.wp.emtlab.service.domain.AuthorService;
import mk.ukim.finki.wp.emtlab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public CountryApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream().map(DisplayCountryDto::from).toList();
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public DisplayCountryDto create(CreateCountryDto createCountryDTO) {
        return null;
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<DisplayCountryDto> deleteById(Long id) {
        return Optional.empty();
    }
}

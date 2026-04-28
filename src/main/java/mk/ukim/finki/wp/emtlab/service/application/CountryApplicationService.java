package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.model.dto.CreateCountryDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    DisplayCountryDto create(CreateCountryDto createCountryDTO);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDTO);

    Optional<DisplayCountryDto> deleteById(Long id);
}

package mk.ukim.finki.wp.emtlab.service.domain;

import mk.ukim.finki.wp.emtlab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Country create(Country country);

    Optional<Country> update(Long id, Country country);

    Optional<Country> deleteById(Long id);
}

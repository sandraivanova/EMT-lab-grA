package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.model.dto.CreateBookDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();

    Optional<DisplayBookDto> findById(Long id);

    DisplayBookDto create(CreateBookDto createBookDTO);

    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDTO);

    Optional<DisplayBookDto> deleteById(Long id);

    Optional<DisplayBookDto> rent(Long id);
}

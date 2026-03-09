package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.model.dto.CreateAuthorDto;
import mk.ukim.finki.wp.emtlab.model.dto.DisplayAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> findById(Long id);

    DisplayAuthorDto create(CreateAuthorDto createAuthorDTO);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDTO);

    Optional<DisplayAuthorDto> deleteById(Long id);
}

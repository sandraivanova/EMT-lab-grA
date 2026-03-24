package mk.ukim.finki.wp.emtlab.service.domain;

import mk.ukim.finki.wp.emtlab.model.dto.BookActivityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookActivityService {
    Page<BookActivityDto> findAll(Pageable pageable);
}
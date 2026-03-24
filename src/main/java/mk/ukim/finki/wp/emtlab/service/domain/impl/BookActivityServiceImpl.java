package mk.ukim.finki.wp.emtlab.service.domain.impl;

import mk.ukim.finki.wp.emtlab.model.domain.BookActivity;
import mk.ukim.finki.wp.emtlab.model.dto.BookActivityDto;
import mk.ukim.finki.wp.emtlab.repository.BookActivityRepository;
import mk.ukim.finki.wp.emtlab.service.domain.BookActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookActivityServiceImpl implements BookActivityService {

    private final BookActivityRepository activityLogRepository;

    public BookActivityServiceImpl(BookActivityRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public Page<BookActivityDto> findAll(Pageable pageable) {
        return activityLogRepository.findAll(pageable)
                .map(this::toDto);
    }

    private BookActivityDto toDto(BookActivity activity) {
        return new BookActivityDto(
                activity.getId(),
                activity.getBookName(),
                activity.getEventTime(),
                activity.getEventType()
        );
    }
}
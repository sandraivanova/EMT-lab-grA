package mk.ukim.finki.wp.emtlab.model.dto;

import java.time.LocalDateTime;

public record BookActivityDto(
        Long id,
        String bookName,
        LocalDateTime eventTime,
        String eventType
) {
}
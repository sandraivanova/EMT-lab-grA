package mk.ukim.finki.wp.emtlab.jobs;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.emtlab.repository.BookCategoryStatisticsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MaterializedViewRefreshScheduler {

    private final BookCategoryStatisticsRepository booksStatsRepository;

    public MaterializedViewRefreshScheduler(BookCategoryStatisticsRepository booksStatsRepository) {
        this.booksStatsRepository = booksStatsRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void refreshBooksStatsView() {
        log.info("Refreshing books_statistics_mv...");

        try {
            booksStatsRepository.refresh();
            log.info("books_statistics_mv successfully refreshed.");
        } catch (Exception e) {
            log.error("Error refreshing books_statistics_mv", e);
        }
    }
}
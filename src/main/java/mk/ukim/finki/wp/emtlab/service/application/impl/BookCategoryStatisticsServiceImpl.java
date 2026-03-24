package mk.ukim.finki.wp.emtlab.service.application.impl;

import mk.ukim.finki.wp.emtlab.model.views.BookCategoryStatisticsView;
import mk.ukim.finki.wp.emtlab.repository.BookCategoryStatisticsRepository;
import mk.ukim.finki.wp.emtlab.service.application.BookCategoryStatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryStatisticsServiceImpl implements BookCategoryStatisticsService {
    private final BookCategoryStatisticsRepository repository;

    public BookCategoryStatisticsServiceImpl(BookCategoryStatisticsRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<BookCategoryStatisticsView> findAll() {
        return repository.findAll();
    }
}

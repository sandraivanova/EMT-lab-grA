package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.model.views.BookCategoryStatisticsView;

import java.util.List;

public interface BookCategoryStatisticsService {
    List<BookCategoryStatisticsView> findAll();

}

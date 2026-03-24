package mk.ukim.finki.wp.emtlab.service.domain;

import mk.ukim.finki.wp.emtlab.model.views.BookView;

import java.util.List;

public interface BookViewService {
    List<BookView> findAll();

}

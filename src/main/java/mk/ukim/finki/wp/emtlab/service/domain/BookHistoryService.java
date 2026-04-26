package mk.ukim.finki.wp.emtlab.service.domain;

import mk.ukim.finki.wp.emtlab.model.domain.BookHistory;

public interface BookHistoryService {
    BookHistory findByBookId(Long bookId);

    void recordAdd(Long bookId);

    void recordUpdate(Long bookId);

    void recordDelete(Long bookId);

    void recordRent(Long bookId);
}

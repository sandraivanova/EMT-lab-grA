package mk.ukim.finki.wp.emtlab.model.projection;

import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.enums.State;

public interface BookSummaryProjection {
    Long getId();

    String getName();

    Category getCategory();

    State getState();

    Integer getAvailableCopies();
}

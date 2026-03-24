package mk.ukim.finki.wp.emtlab.events;

public record BookRentedEvent(
        Long bookId,
        String bookName,
        Integer availableCopies
) {
}

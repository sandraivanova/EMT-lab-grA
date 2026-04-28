package mk.ukim.finki.wp.emtlab.model.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productName) {
        super("Not enough stock for %s.".formatted(productName));
    }
}

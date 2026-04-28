package mk.ukim.finki.wp.emtlab.model.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("A product with id %d does not exist.".formatted(id));
    }
}

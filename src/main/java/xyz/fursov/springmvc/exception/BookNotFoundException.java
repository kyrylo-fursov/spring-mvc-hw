package xyz.fursov.springmvc.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message, IllegalArgumentException e) {
        super(message, e);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
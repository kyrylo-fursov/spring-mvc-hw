package xyz.fursov.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Component
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<BookExceptionResponse> handleBookNotFoundException(
            BookNotFoundException e) {
        BookExceptionResponse data = new BookExceptionResponse();
        data.setMessage(e.getMessage());
        data.setMessage(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BookExceptionResponse>  handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        BookExceptionResponse data = new BookExceptionResponse();
        data.setMessage(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<BookExceptionResponse>  handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        BookExceptionResponse data = new BookExceptionResponse();
        data.setMessage(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}


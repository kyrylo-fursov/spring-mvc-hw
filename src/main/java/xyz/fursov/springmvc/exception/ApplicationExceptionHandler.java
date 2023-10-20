package xyz.fursov.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

@Component
@RestControllerAdvice
public class ApplicationExceptionHandler {
      @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
      protected ResponseEntity<BookExceptionResponse> handleValidationExceptions(Exception e) {
          BookExceptionResponse data = new BookExceptionResponse();
          data.setMessage(e.getMessage());
          return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
      }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BookExceptionResponse> handleEntityNotFoundException(
            EntityNotFoundException e) {
        BookExceptionResponse data = new BookExceptionResponse();
        data.setMessage(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}


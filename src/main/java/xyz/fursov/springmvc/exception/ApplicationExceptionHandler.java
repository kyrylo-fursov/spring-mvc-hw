package xyz.fursov.springmvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<BookIncorrectData> handleBookNotFoundException(
            BookNotFoundException e) {
        BookIncorrectData data = new BookIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BookIncorrectData> handleException(
            Exception e) {
        BookIncorrectData data = new BookIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(BookNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public String handleBookNotFoundException(BookNotFoundException ex) {
//        return ex.getMessage();
//    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBadRequestException(BadRequestException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleBookAlreadyExistsException(BookAlreadyExistsException ex) {
        return ex.getMessage();
    }


}

package xyz.fursov.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.fursov.springmvc.dao.exceptions.BadRequestException;
import xyz.fursov.springmvc.dao.exceptions.BookNotFoundException;
import xyz.fursov.springmvc.entity.Book;
import xyz.fursov.springmvc.service.BookService;
import xyz.fursov.springmvc.service.BookServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

//    @PostMapping
//    public ResponseEntity<String> addBook(@RequestParam int id, @RequestParam String name, @RequestParam String author, @RequestParam int year) {
//        Book newBook = new Book(id, name, author, year);
//        bookService.addBook(newBook);
//        return ResponseEntity.ok("Book added successfully");
//    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestParam int id, @RequestParam String name, @RequestParam String author, @RequestParam int year) {
        if (!isValidId(id)) {
            throw new BadRequestException("Invalid value for 'id' parameter");
        }
        if (!isValidYear(year)) {
            throw new BadRequestException("Invalid value for 'year' parameter");
        }
        Book newBook = new Book(id, name, author, year);
        bookService.addBook(newBook);
        return ResponseEntity.ok("Book added successfully");
    }

    private boolean isValidId(long id) {
        return id > 0;
    }

    private boolean isValidYear(int year) {
        return year > 0;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam("id") int id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestParam("id") int id, @RequestParam("book") Book updatedBook) {
        try {
            bookService.updateBook(id, updatedBook);
            return ResponseEntity.ok("Book updated successfully");
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

package xyz.fursov.springmvc.service;

import org.springframework.data.domain.Pageable;
import xyz.fursov.springmvc.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks(Pageable pageable);
    List<Book> getAllBooks(String nameRegex, Pageable pageable);
    Book getBookById(int id);
    void addBook(Book book);
    void deleteBook(int id);
    void updateBook(Book book);
}

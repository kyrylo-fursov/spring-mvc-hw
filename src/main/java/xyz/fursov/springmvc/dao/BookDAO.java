package xyz.fursov.springmvc.dao;

import org.springframework.data.domain.Pageable;
import xyz.fursov.springmvc.entity.Book;
import java.util.List;

public interface BookDAO {
     List<Book> getAllBooks(Pageable pageable);
     List<Book> getAllBooks(String nameRegex, Pageable pageable);
     Book getBookById(int id);
     void addBook(Book book);
     void deleteBook(int id);
     void updateBook(Book updatedBook);
}

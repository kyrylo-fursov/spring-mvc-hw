package xyz.fursov.springmvc.service;

import org.springframework.stereotype.Component;
import xyz.fursov.springmvc.entity.Book;

import java.util.List;

@Component
public class BookService {
    public List<Book> getAllBooks() {
        return List.of(
                new Book(1L, "War and Peace", "Leo Tolstoy", 1869),
                new Book(2L, "Anna Karenina", "Leo Tolstoy", 1877),
                new Book(3L, "The Death of Ivan Ilyich", "Leo Tolstoy", 1886)
        );
    }
}

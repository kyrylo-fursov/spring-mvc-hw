package xyz.fursov.springmvc.dao;

import org.springframework.stereotype.Repository;
import xyz.fursov.springmvc.entity.Book;

import java.util.List;

@Repository
public class ListBookDAO implements BookDAO {
    List<Book> books = List.of(
            new Book(1, "War and Peace", "Leo Tolstoy", 1869),
            new Book(2, "Anna Karenina", "Leo Tolstoy", 1877),
            new Book(3, "The Death of Ivan Ilyich", "Leo Tolstoy", 1886)
    );

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void deleteBook(long id) {

    }

    @Override
    public void updateBook(long book) {

    }
}

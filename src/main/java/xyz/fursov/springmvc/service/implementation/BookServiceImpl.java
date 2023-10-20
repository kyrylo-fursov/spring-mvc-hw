package xyz.fursov.springmvc.service.implementation;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.fursov.springmvc.dao.BookDAO;
import xyz.fursov.springmvc.entity.Book;
import xyz.fursov.springmvc.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAllBooks(Pageable pageable){
        return bookDAO.getAllBooks(pageable);
    }

    @Override
    public List<Book> getAllBooks(String nameRegex, Pageable pageable){
        return bookDAO.getAllBooks(nameRegex, pageable);
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }
}

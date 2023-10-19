package xyz.fursov.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.fursov.springmvc.dao.BookDAO;
import xyz.fursov.springmvc.exception.BookNotFoundException;
import xyz.fursov.springmvc.dao.DAOFactory;
import xyz.fursov.springmvc.entity.Book;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(@Value("${dao.implementation}") String daoType, Map<String, DAOFactory> daoFactoryMap) {
        if (!daoFactoryMap.containsKey(daoType + "DAOFactory")) {
            throw new RuntimeException("Unknown DAO implementation: " + daoType);
        }
        DAOFactory daoFactory = daoFactoryMap.get(daoType + "DAOFactory");
        this.bookDAO = daoFactory.getBookDAO();
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
        try {
            return bookDAO.getBookById(id);
        } catch (IllegalArgumentException ex) {
            throw new BookNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBook(int id) {
        try {
            bookDAO.deleteBook(id);
        } catch (IllegalArgumentException ex) {
            throw new BookNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            bookDAO.updateBook(book);
        } catch (IllegalArgumentException ex) {
            throw new BookNotFoundException(ex.getMessage());
        }
    }
}

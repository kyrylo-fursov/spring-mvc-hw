package xyz.fursov.springmvc.dao.list;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import xyz.fursov.springmvc.dao.BookDAO;
import xyz.fursov.springmvc.entity.Book;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(name = "dao.type", havingValue = "list")
public class ListBookDAO implements BookDAO {
    private final List<Book> books;

    public ListBookDAO() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void initialize() {
        addBook(new Book(1, "Book 1", "Author 1", 2000));
        addBook(new Book(2, "Book 2", "Author 2", 2005));
        addBook(new Book(3, "Book 3", "Author 3", 2010));
    }

    @Override
    public List<Book> getAllBooks(Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), books.size());
        return books.subList(start, end);
    }

    @Override
    public List<Book> getAllBooks(String nameRegex, Pageable pageable) {
        if (nameRegex == null || nameRegex.isEmpty()) {
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), books.size());
            return books.subList(start, end);
        } else {
            return books.stream()
                    .filter(book -> book.getName().matches(nameRegex))
                    .skip(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No book with ID " + id + " found."));
    }

    @Override
    public void addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getId() == book.getId() || book.getId() == 0) {
                int newId = generateNewId();
                book.setId(newId);
                break;
            }
        }
        books.add(book);
    }

    private int generateNewId() {
        int newId = 1;
        while (isIdAlreadyExists(newId)) {
            newId++;
        }
        return newId;
    }

    private boolean isIdAlreadyExists(int id) {
        for (Book existingBook : books) {
            if (existingBook.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteBook(int id) {
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (!removed) {
            throw new EntityNotFoundException("No book with ID " + id + " found.");
        }
    }

    @Override
    public void updateBook(Book updatedBook) {
        int id = updatedBook.getId();
        Optional<Book> bookToUpdate = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();

        if (bookToUpdate.isPresent()) {
            Book existingBook = bookToUpdate.get();
            existingBook.setName(updatedBook.getName());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setYear(updatedBook.getYear());
        } else {
            throw new EntityNotFoundException("No book with ID " + id + " found.");
        }
    }
}

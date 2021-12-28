package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getBooks();
    Optional<Book> getBookById(int bookId);
    void updateBook(Book book);
}

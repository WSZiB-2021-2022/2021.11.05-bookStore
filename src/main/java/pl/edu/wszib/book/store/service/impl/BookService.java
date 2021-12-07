package pl.edu.wszib.book.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.service.IBookService;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    DB database;

    public List<Book> getAllBooks() {
        return this.database.getBooks();
    }
}

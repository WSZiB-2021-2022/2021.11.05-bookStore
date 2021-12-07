package pl.edu.wszib.book.store.service;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
}

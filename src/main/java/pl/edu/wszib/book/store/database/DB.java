package pl.edu.wszib.book.store.database;

import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class DB {
    private List<Book> books = new ArrayList<>();

    public DB() {
        this.books.add(
                new Book(1,
                        "English 4 IT. Praktyczny " +
                                "kurs języka angielskiego dla " +
                                "specjalistów IT i nie tylko",
                        "Beata Błaszczyk",
                        12.90,
                        "978-83-283-3409-0"));

        this.books.add(new Book(2,
                "Czysty kod. Podręcznik dobrego programisty",
                "Robert C. Martin",
                55.20,
                "978-83-283-0234-1"));

        this.books.add(new Book(3,
                "Algorytmy. Ilustrowany przewodnik",
                "Aditya Bhargava",
                43.91, "978-83-283-3445-8"));
    }

    public List<Book> getBooks() {
        return books;
    }
}

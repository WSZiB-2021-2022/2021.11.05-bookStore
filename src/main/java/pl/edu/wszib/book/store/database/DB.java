package pl.edu.wszib.book.store.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DB {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public DB() {
        this.books.add(
                new Book(1,
                        "English 4 IT. Praktyczny " +
                                "kurs języka angielskiego dla " +
                                "specjalistów IT i nie tylko",
                        "Beata Błaszczyk",
                        12.90,
                        "978-83-283-3409-0",
                        10));

        this.books.add(new Book(2,
                "Czysty kod. Podręcznik dobrego programisty",
                "Robert C. Martin",
                55.20,
                "978-83-283-0234-1",
                10));

        this.books.add(new Book(3,
                "Algorytmy. Ilustrowany przewodnik",
                "Aditya Bhargava",
                43.91, "978-83-283-3445-8",
                15));

        this.users.add(new User(1, "Mateusz", "Bereda", "admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User(2, "Jan", "Malinowski", "user", DigestUtils.md5Hex("user")));
    }

    public List<Book> getBooks() {
        return books;
    }

    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<Book> getBookById(int bookId) {
        for(Book book : this.books) {
            if(book.getId() == bookId) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getId() == userId) {
                result.add(order);
            }
        }

        return result;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}

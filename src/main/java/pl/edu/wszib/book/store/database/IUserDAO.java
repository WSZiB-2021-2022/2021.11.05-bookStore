package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}

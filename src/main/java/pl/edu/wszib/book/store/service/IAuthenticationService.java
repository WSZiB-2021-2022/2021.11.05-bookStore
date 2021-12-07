package pl.edu.wszib.book.store.service;

import pl.edu.wszib.book.store.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}

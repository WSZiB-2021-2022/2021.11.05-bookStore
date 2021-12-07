package pl.edu.wszib.book.store.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.exceptions.LoginAlreadyUseException;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.model.view.RegisterUser;
import pl.edu.wszib.book.store.service.IAuthenticationService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticateService implements IAuthenticationService {

    @Autowired
    DB database;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.database.getUserByLogin(login);

        if(user.isEmpty() ||
                !user.get().getPass().equals(DigestUtils.md5Hex(password))) {
            return;
        }
        this.sessionObject.setUser(user.get());
    }

    @Override
    public void register(RegisterUser registerUser) {
        Optional<User> userBox = this.database.getUserByLogin(registerUser.getLogin());

        if(userBox.isPresent()) {
            throw new LoginAlreadyUseException();
        }

        registerUser.setPass(DigestUtils.md5Hex(registerUser.getPass()));
        this.database.addUser(registerUser);
    }
}

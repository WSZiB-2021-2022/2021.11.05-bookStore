package pl.edu.wszib.book.store.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class AuthenticateService {

    @Autowired
    DB database;

    @Resource
    SessionObject sessionObject;

    public boolean authenticate(String login, String password) {
        Optional<User> user = this.database.getUserByLogin(login);

        if(user.isEmpty() ||
                !user.get().getPass().equals(DigestUtils.md5Hex(password))) {
            return false;
        }
        this.sessionObject.setLogged(true);
        return true;
    }
}

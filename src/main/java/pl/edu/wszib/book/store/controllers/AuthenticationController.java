package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.exceptions.AuthValidationException;
import pl.edu.wszib.book.store.service.AuthenticateService;
import pl.edu.wszib.book.store.validators.LoginValidator;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticateService authenticateService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        try {
            LoginValidator.validateLogin(login);
            LoginValidator.validatePass(password);
        } catch (AuthValidationException e) {
            return "redirect:/login";
        }

        if(this.authenticateService.authenticate(login, password)) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }
}

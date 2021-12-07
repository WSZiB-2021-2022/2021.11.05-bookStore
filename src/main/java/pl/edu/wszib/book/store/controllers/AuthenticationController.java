package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.exceptions.AuthValidationException;
import pl.edu.wszib.book.store.exceptions.LoginAlreadyUseException;
import pl.edu.wszib.book.store.model.view.RegisterUser;
import pl.edu.wszib.book.store.service.IAuthenticationService;
import pl.edu.wszib.book.store.service.impl.AuthenticateService;
import pl.edu.wszib.book.store.session.SessionObject;
import pl.edu.wszib.book.store.validators.LoginValidator;
import pl.edu.wszib.book.store.validators.RegisterValidator;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticateService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
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

        this.authenticateService.authenticate(login, password);

        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.setUser(null);
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("ruser", new RegisterUser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser) {
        try {
            RegisterValidator.validateName(registerUser.getName());
            RegisterValidator.validateSurname(registerUser.getSurname());
            LoginValidator.validateLogin(registerUser.getLogin());
            LoginValidator.validatePass(registerUser.getPass());
            checkPasswords(registerUser.getPass(), registerUser.getPassword2());
            this.authenticateService.register(registerUser);
        } catch (AuthValidationException | LoginAlreadyUseException e) {
            return "redirect:/register";
        }

        return "redirect:/main";
    }

    private void checkPasswords(String pass1, String pass2) {
        if(pass1 == null || !pass1.equals(pass2)) {
            throw new AuthValidationException("Incorrect passwords !");
        }
    }
}

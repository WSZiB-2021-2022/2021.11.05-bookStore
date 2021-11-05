package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.model.Book;

import java.util.List;

@Controller
public class CommonController {

    @Autowired
    DB database;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        List<Book> books = this.database.getBooks();
        model.addAttribute("books", books);
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }
}

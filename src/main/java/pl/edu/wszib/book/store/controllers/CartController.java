package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.model.view.BookOrderPosition;
import pl.edu.wszib.book.store.service.CartService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add/{bookId}")
    public String addBookToCart(@PathVariable Integer bookId) {
        this.cartService.addBookToCart(bookId);
        return "redirect:/main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart",
                this.sessionObject.getCart());

        return "cart";
    }
}
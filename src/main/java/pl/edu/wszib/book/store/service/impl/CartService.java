package pl.edu.wszib.book.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.service.ICartService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    DB database;

    @Resource
    SessionObject sessionObject;

    public void addBookToCart(int bookId) {
        Optional<Book> bookBox = this.database.getBookById(bookId);

        if(bookBox.isEmpty()) {
            return;
        }

        Book book = bookBox.get();
        if(!(book.getQuantity() > 0)) {
            return;
        }

        for(OrderPosition orderPosition : this.sessionObject
                .getCart().getOrderPositions()) {
            if(orderPosition.getBook().getId() == bookId) {
                orderPosition.incrementQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(book, 1);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}

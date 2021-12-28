package pl.edu.wszib.book.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.DB;
import pl.edu.wszib.book.store.database.IBookDAO;
import pl.edu.wszib.book.store.database.IOrderDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.service.IOrderService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceNew implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), this.sessionObject.getCart().getOrderPositions());
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Book> bookBox = this.bookDAO.getBookById(orderPosition.getBook().getId());
            if(bookBox.isPresent()) {
                bookBox.get().setQuantity(bookBox.get().getQuantity() - orderPosition.getQuantity());
                this.bookDAO.updateBook(bookBox.get());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}

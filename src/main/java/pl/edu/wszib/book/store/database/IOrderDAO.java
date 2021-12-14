package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}

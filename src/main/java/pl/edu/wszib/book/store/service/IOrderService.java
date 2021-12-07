package pl.edu.wszib.book.store.service;

import pl.edu.wszib.book.store.model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}

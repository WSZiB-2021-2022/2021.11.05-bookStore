package pl.edu.wszib.book.store.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private User user;
    private double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private List<OrderPosition> orderPositions = new ArrayList<>();
    private LocalDateTime date;

    public Order(int id, User user, double price, Status status, List<OrderPosition> orderPositions, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.price = price;
        this.status = status;
        this.orderPositions = orderPositions;
        this.date = date;
    }

    public Order(User user, List<OrderPosition> orderPositions) {
        this.user = user;
        this.status = Status.NEW;
        this.orderPositions = orderPositions;
        date = LocalDateTime.now();
        this.price = 0;
        for(OrderPosition orderPosition : orderPositions) {
            this.price += orderPosition.getBook().getPrice() * orderPosition.getQuantity();
        }
        this.price = Math.round(this.price*100)/100.0;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public enum Status {
        NEW,
        PAID,
        SENT,
        DELIVERED
    }
}

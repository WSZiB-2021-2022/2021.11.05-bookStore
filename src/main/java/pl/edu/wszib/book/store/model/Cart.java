package pl.edu.wszib.book.store.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    final List<OrderPosition> orderPositions = new ArrayList<>();

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public double getSum() {
        double sum = 0.0;
        for(OrderPosition orderPosition : this.orderPositions) {
            sum += orderPosition.getQuantity() * orderPosition.getBook().getPrice();
        }

        return sum;
    }
}

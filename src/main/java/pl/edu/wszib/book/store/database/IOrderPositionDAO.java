package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.OrderPosition;
import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}

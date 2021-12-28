package pl.edu.wszib.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.database.IOrderDAO;
import pl.edu.wszib.book.store.database.IOrderPositionDAO;
import pl.edu.wszib.book.store.database.IUserDAO;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.model.OrderPosition;
import pl.edu.wszib.book.store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    Connection connection;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void addOrder(Order order) {
        try {
            String sql = "INSERT INTO torder VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setInt(2, order.getUser().getId());
            preparedStatement.setDouble(3, order.getPrice());
            preparedStatement.setString(4, order.getStatus().toString());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(order.getDate()));

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                order.setId(rs.getInt(1));
            }

            for(OrderPosition orderPosition : order.getOrderPositions()) {
                this.orderPositionDAO.addOrderPosition(orderPosition, order.getId());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder WHERE user_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));

                Optional<User> userBox = this.userDAO.getUserById(userId);
                order.setUser(userBox.get());

                order.setPrice(rs.getDouble("price"));
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                order.setDate(rs.getTimestamp("date").toLocalDateTime());

                List<OrderPosition> orderPositions = this.orderPositionDAO.getOrderPositionsByOrderId(order.getId());
                order.setOrderPositions(orderPositions);

                result.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}

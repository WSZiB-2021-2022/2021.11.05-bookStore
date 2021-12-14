package pl.edu.wszib.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.database.IOrderDAO;
import pl.edu.wszib.book.store.model.Order;

import java.sql.*;
import java.util.List;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    Connection connection;

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

            //TODO zapisa wszystkich order pozycji

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return null;
    }
}

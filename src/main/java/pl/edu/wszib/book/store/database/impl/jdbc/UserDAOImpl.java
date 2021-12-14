package pl.edu.wszib.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.database.IUserDAO;
import pl.edu.wszib.book.store.model.User;

import java.sql.*;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setPass(rs.getString("pass"));

                return Optional.of(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO tuser VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPass());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

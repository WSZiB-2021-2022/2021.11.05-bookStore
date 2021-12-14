package pl.edu.wszib.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.database.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAOImpl implements IBookDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setQuantity(rs.getInt("quantity"));
                book.setIsbn(rs.getString("isbn"));

                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }

    @Override
    public Optional<Book> getBookById(int bookId) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setIsbn(resultSet.getString("isbn"));

                return Optional.of(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }
}

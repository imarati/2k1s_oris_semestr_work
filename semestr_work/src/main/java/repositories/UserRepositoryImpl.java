package repositories;

import models.User;
import org.springframework.security.access.method.P;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository{
    Connection connection;

    String INSERT_INTO_USERS = "INSERT INTO users (name, surname, email, password) VALUES (?,?,?,?)";
    String SELECT_FROM_USERS_WHERE_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";

    public UserRepositoryImpl (Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());

            int rows = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .build();

            return user;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

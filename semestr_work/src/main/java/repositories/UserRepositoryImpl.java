package repositories;

import interfaces.UserRepository;
import models.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    private DataSource dataSource;

    String INSERT_INTO_USERS = "INSERT INTO users (name, surname, email, password) VALUES (?,?,?,?)";
    String INSERT_INTO_USERS_ROLES = "INSERT INTO users_roles VALUES (?,?)";
    String SELECT_FROM_USERS_WHERE_EMAIL = "SELECT * FROM users WHERE email=?";

    public UserRepositoryImpl (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());

            int rows = preparedStatement.executeUpdate();

            User user = findByEmail(entity.getEmail());

            preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_ROLES);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setInt(2, 1);

            rows = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAdmin(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());

            int rows = preparedStatement.executeUpdate();

            User user = findByEmail(entity.getEmail());

            preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_ROLES);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setInt(2, 2);

            rows = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_EMAIL);
            preparedStatement.setString(1, email);

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

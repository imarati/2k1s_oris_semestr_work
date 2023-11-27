package repositories;

import interfaces.RoleRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepositoryImpl implements RoleRepository {
    private DataSource dataSource;

    private String SELECT_FROM_USERS_ROLES_WHERE_USER_ID = "SELECT * FROM users_roles WHERE user_id=?";
    private String SELECT_FROM_ROLES_WHERE_ID = "SELECT * FROM roles WHERE id=?";

    public RoleRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public String findByUserId(long userId) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_ROLES_WHERE_USER_ID);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int roleId = resultSet.getInt("role_id");

            preparedStatement = connection.prepareStatement(SELECT_FROM_ROLES_WHERE_ID);
            preparedStatement.setInt(1, roleId);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return  resultSet.getString("name");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

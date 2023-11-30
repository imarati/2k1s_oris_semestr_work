package repositories;

import interfaces.UsersGamesRepository;
import models.Game;
import models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersGamesRepositoryImpl implements UsersGamesRepository {
    private DataSource dataSource;

    private String INSERT_INTO_USERS_GAMES = "INSERT INTO users_games (user_id, game_id) VAlUES(?,?)";
    private String SELECT_FROM_USERS_GAMES_WHERE_USER_ID = "SELECT * FROM users_games WHERE user_id=?";
    private String SELECT_FROM_USERS_GAMES_WHERE_GAME_ID = "SELECT * FROM users_games WHERE game_id=?";

    public UsersGamesRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(long userId, long gameId) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_GAMES);

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, gameId);

            int rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findByUserId(long userId) {
        List<Integer> result = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_GAMES_WHERE_USER_ID);

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int gameId = resultSet.getInt("game_id");

                result.add(gameId);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findByGameId(long gameId) {
        List<Integer> result = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_GAMES_WHERE_GAME_ID);

            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");

                result.add(user_id);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

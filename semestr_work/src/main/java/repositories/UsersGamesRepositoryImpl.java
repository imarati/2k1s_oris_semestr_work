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
    private String SELECT_FROM_USERS_GAMES_WHERE_USER_ID = "SELECT * WHERE user_id=?";
    private String SELECT_FROM_USERS_GAMES_WHERE_GAME_ID = "SELECT * WHERE game_id=?";

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
    public List<Game> findByUserId(long userId) {
        List<Game> result = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_GAMES_WHERE_USER_ID);

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Game game = Game.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .review(resultSet.getString("review"))
                        .fileId(resultSet.getLong("file_id"))
                        .build();

                result.add(game);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findByGameId(long gameId) {
        List<User> result = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_GAMES_WHERE_USER_ID);

            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();

                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

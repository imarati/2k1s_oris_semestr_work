package repositories;

import interfaces.GameRepository;
import models.Game;
import models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    DataSource dataSource;

    private String INSERT_INTO_GAME = "INSERT INTO games (name, review, file_id) VAlUES(?,?,?)";
    private String SELECT_FROM_GAME_WHERE_ID = "SELECT * FROM games WHERE id=?";
    private String SELECT_FROM_GAME = "SELECT * FROM games";

    public GameRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Game entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GAME);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getReview());
            preparedStatement.setLong(3, entity.getFileId());

            int rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Game findById(long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_GAME_WHERE_ID);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return Game.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .review(resultSet.getString("review"))
                    .fileId(resultSet.getLong("file_id"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Game> findAll() {
        List<Game> gameList = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_GAME);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Game game = Game.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .review(resultSet.getString("review"))
                        .fileId(resultSet.getLong("file_id"))
                        .build();

                gameList.add(game);
            }

            return gameList;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

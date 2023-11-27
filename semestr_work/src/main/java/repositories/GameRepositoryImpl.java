package repositories;

import interfaces.GameRepository;
import models.Game;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRepositoryImpl implements GameRepository {
    DataSource dataSource;

    private String INSERT_INTO_GAME = "INSERT INTO games (name, review, file_id) VAlUES(?,?,?)";
    private String SELECT_FROM_GAME_WHERE_ID = "SELECT * FROM games WHERE id=?";

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
}

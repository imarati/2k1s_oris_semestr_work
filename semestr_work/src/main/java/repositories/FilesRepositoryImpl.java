package repositories;

import interfaces.FilesRepository;
import models.FileInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilesRepositoryImpl implements FilesRepository {
    private DataSource dataSource;

    String INSERT_INTO_FILE = "INSERT INRO file ('storage_file_name', 'original_file_name', 'type', 'size') " +
            "VALUES(?,?,?,?)";
    String SELECT_FROM_FILE_WHERE_ID = "SELECT * FROM file WHERE id=?";

    public FilesRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(FileInfo entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_FILE);
            preparedStatement.setString(1, entity.getStorageFileName());
            preparedStatement.setString(2, entity.getOriginalFileName());
            preparedStatement.setString(3, entity.getType());
            preparedStatement.setLong(4, entity.getSize());

            int rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo findById(long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_FILE_WHERE_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            FileInfo fileInfo = FileInfo.builder()
                    .id(resultSet.getLong("id"))
                    .storageFileName(resultSet.getString("storage_file_name"))
                    .originalFileName(resultSet.getString("original_file_name"))
                    .type(resultSet.getString("type"))
                    .size(resultSet.getLong("size"))
                    .build();

            return fileInfo;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package repositories;

import interfaces.FileRepository;
import models.FileInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileRepositoryImpl implements FileRepository {
    private DataSource dataSource;

    String INSERT_INTO_FILE = "INSERT INTO file (storage_file_name, original_file_name, type, size) " +
            "VALUES(?,?,?,?)";
    String SELECT_FROM_FILE_WHERE_ID = "SELECT * FROM file WHERE id=?";
    String SELECT_FROM_FILE_WHERE_STORAGE_FILE_NAME = "SELECT * FROM file WHERE storage_file_name=?";

    public FileRepositoryImpl(DataSource dataSource) {
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

            return FileInfo.builder()
                    .id(resultSet.getLong("id"))
                    .storageFileName(resultSet.getString("storage_file_name"))
                    .originalFileName(resultSet.getString("original_file_name"))
                    .type(resultSet.getString("type"))
                    .size(resultSet.getLong("size"))
                    .build();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo findByStorageFileName(String storageFileName) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_FILE_WHERE_STORAGE_FILE_NAME);
            preparedStatement.setString(1, storageFileName);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return FileInfo.builder()
                    .id(resultSet.getLong("id"))
                    .storageFileName(resultSet.getString("storage_file_name"))
                    .originalFileName(resultSet.getString("original_file_name"))
                    .type(resultSet.getString("type"))
                    .size(resultSet.getLong("size"))
                    .build();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

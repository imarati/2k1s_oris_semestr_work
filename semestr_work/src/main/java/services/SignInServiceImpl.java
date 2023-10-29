package services;

import dto.SignInForm;
import exceptions.InvalidEmailOrPasswordException;
import models.User;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignInServiceImpl implements SignInService{
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semester_work";

    private UserRepository userRepository;


    @Override
    public User signIn(SignInForm signInForm) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            userRepository = new UserRepositoryImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            return userRepository.findByEmailAndPassword(signInForm.getEmail(), signInForm.getPassword());
        }
        catch (RuntimeException e) {
            throw new InvalidEmailOrPasswordException("Invalid email or password!");
        }
    }
}

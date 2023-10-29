package services;

import dto.SignUpForm;
import models.User;
import repositories.UserRepository;
import repositories.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService{
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semester_work";

    private UserRepository userRepository;

    @Override
    public void signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .build();


        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            userRepository = new UserRepositoryImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        userRepository.save(user);
    }
}

package services;

import dto.SignUpForm;
import interfaces.SignUpService;
import models.User;
import interfaces.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.UserRepositoryImpl;
import sun.security.util.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();

        userRepository.save(user);
    }
}

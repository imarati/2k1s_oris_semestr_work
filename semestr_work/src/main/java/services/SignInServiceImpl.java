package services;

import dto.SignInForm;
import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;
import interfaces.SignInService;
import models.User;
import interfaces.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignInServiceImpl implements SignInService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public User signIn(SignInForm signInForm) {
        User user;

        try {
            user =  userRepository.findByEmail(signInForm.getEmail());
        }
        catch (RuntimeException e) {
            throw new InvalidEmailException("Invalid email!");
        }

        System.out.println(passwordEncoder.matches(signInForm.getPassword(), user.getPassword()));
        if(passwordEncoder.matches(signInForm.getPassword(), user.getPassword())){
            return user;
        }

        throw new InvalidPasswordException("Invalid password!");
    }
}

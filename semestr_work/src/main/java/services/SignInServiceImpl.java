package services;

import dto.SignInForm;
import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;
import interfaces.RoleRepository;
import interfaces.SignInService;
import models.User;
import interfaces.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignInServiceImpl implements SignInService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
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

        if(passwordEncoder.matches(signInForm.getPassword(), user.getPassword())){
            return user;
        }

        throw new InvalidPasswordException("Invalid password!");
    }
}

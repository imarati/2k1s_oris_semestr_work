package services;

import dto.SignUpForm;
import interfaces.AddAdminService;
import interfaces.UserRepository;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AddAdminServiceImpl implements AddAdminService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AddAdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void addAdmin(SignUpForm signUpForm) {
        User user = User.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();

        userRepository.saveAdmin(user);
    }
}

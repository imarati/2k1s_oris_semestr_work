package services;

import dto.SignInForm;
import models.User;

public interface SignInService {
    User signIn(SignInForm signInForm);
}

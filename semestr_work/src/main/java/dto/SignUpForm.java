package dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class SignUpForm {
    private String name;
    private String surname;
    private String email;
    private String password;
}

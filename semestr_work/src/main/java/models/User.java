package models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<Game> games;
}

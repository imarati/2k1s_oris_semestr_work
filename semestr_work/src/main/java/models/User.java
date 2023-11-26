package models;

import lombok.*;

import java.util.List;

@Data
@Builder
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<Game> games;
}

package models;

import lombok.*;

@Data
@Builder
public class Game {
    private long id;
    private String name;
    private String review;
}

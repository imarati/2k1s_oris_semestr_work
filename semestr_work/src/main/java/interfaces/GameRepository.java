package interfaces;

import models.Game;

import java.util.List;

public interface GameRepository extends CrudRepository<Game>{
    Game findById(long id);
    List<Game> findAll();
}

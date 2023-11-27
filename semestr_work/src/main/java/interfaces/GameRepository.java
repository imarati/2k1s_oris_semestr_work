package interfaces;

import models.Game;

public interface GameRepository extends CrudRepository<Game>{
    Game findById(long id);
}

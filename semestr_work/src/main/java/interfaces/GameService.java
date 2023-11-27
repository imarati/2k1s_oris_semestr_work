package interfaces;

import models.Game;

public interface GameService extends CrudRepository<Game>{
    void addGame(Game game);
    Game getGame(long gameId);
}

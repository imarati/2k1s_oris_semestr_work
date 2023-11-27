package interfaces;

import models.Game;

public interface GameService{
    void addGame(Game game);
    Game getGame(long gameId);
}

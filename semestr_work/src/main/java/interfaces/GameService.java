package interfaces;

import models.Game;

import java.util.List;

public interface GameService{
    void addGame(Game game);
    Game getGame(long gameId);
    List<Game> getAll();
}

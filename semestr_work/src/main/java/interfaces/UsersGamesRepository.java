package interfaces;

import models.Game;
import models.User;

import java.util.List;

public interface UsersGamesRepository{
    void save(long userId, long gameId);
    List<Game> findByUserId(long userId);
    List<User> findByGameId(long gameId);
}

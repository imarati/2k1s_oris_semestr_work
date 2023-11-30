package interfaces;

import models.Game;
import models.User;

import java.util.List;

public interface UsersGamesRepository{
    void save(long userId, long gameId);
    List<Integer> findByUserId(long userId);
    List<Integer> findByGameId(long gameId);
}

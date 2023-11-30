package interfaces;

import models.Game;
import models.User;

import java.util.List;

public interface UsersGamesService {
    List<Game> getUserGames(long userId);
    List<User> getGameUsers(long gameId);
}

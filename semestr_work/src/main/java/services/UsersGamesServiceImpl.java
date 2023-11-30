package services;

import interfaces.GameRepository;
import interfaces.UserRepository;
import interfaces.UsersGamesRepository;
import interfaces.UsersGamesService;
import models.Game;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersGamesServiceImpl implements UsersGamesService {
    private UsersGamesRepository usersGamesRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;

    public UsersGamesServiceImpl(UsersGamesRepository usersGamesRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.usersGamesRepository = usersGamesRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Game> getUserGames(long userId) {
        List<Integer> gameIdList = usersGamesRepository.findByUserId(userId);
        List<Game> games = new ArrayList<>();

        for (int gameId: gameIdList) {
            games.add(gameRepository.findById(gameId));
        }

        return games;
    }

    @Override
    public List<User> getGameUsers(long gameId) {
        List<Integer> userIdList = usersGamesRepository.findByGameId(gameId);
        List<User> users = new ArrayList<>();

        for (int userId: userIdList) {
            users.add(userRepository.findById(userId));
        }

        return users;
    }
}

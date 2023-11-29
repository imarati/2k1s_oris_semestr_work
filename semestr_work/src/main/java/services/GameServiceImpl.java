package services;

import interfaces.GameRepository;
import interfaces.GameService;
import models.Game;

import java.util.List;

public class GameServiceImpl implements GameService {
    GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public void addGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Game getGame(long gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}

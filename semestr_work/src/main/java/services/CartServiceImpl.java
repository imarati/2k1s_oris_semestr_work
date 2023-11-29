package services;

import interfaces.CartSevice;
import interfaces.UsersGamesRepository;
import models.Cart;
import models.Game;

import java.util.List;

public class CartServiceImpl implements CartSevice {
    private Cart cart;
    private UsersGamesRepository usersGamesRepository;

    public CartServiceImpl(Cart cart, UsersGamesRepository usersGamesRepository) {
        this.cart = cart;
        this.usersGamesRepository = usersGamesRepository;
    }

    @Override
    public void addToCart(Game game) {
        List<Game> games = cart.getGamesList();
        games.add(game);
    }

    @Override
    public List<Game> getFromCart() {
        return cart.getGamesList();
    }

    @Override
    public boolean bye(long userId) {
        List<Game> games = cart.getGamesList();

        for (Game game : games) {
            usersGamesRepository.save(userId, game.getId());
        }

        games.clear();
        return true;
    }
}

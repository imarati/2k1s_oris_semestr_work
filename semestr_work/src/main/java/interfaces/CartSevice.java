package interfaces;

import models.Game;

import java.util.List;

public interface CartSevice {
    void addToCart(Game game);
    List<Game> getFromCart();
    boolean bye(long userId);
}

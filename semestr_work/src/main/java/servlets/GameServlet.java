package servlets;

import interfaces.CartSevice;
import interfaces.GameService;
import models.Cart;
import models.FileInfo;
import models.Game;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;
    private CartSevice cartSevice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        gameService = (GameService) servletContext.getAttribute("gameService");
        cartSevice = (CartSevice) servletContext.getAttribute("cartSevice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gameId = req.getParameter("id");
        Game game = gameService.getGame(Long.parseLong(gameId));
        req.setAttribute("game", game);
        req.getRequestDispatcher("/jsp/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gameId = req.getParameter("id");
        Game game = gameService.getGame(Long.parseLong(gameId));
        cartSevice.addToCart(game);
    }
}

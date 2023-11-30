package servlets;

import interfaces.GameService;
import interfaces.UsersGamesService;
import models.Game;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/players")
public class PlayersServlet extends HttpServlet {
    UsersGamesService usersGamesService;
    GameService gameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersGamesService = (UsersGamesService) servletContext.getAttribute("usersGamesService");
        gameService = (GameService) servletContext.getAttribute("gameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gameId = req.getParameter("id");
        System.out.println(gameId);
        List<User> users = usersGamesService.getGameUsers(Long.parseLong(gameId));
        System.out.println(users.get(0).getName());
        req.setAttribute("users", users);
        req.getRequestDispatcher("/jsp/Players.jsp").forward(req, resp);
    }
}

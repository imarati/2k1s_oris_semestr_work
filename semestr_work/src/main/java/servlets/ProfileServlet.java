package servlets;

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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    UsersGamesService usersGamesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersGamesService = (UsersGamesService) servletContext.getAttribute("usersGamesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");
        List<Game> games = usersGamesService.getUserGames(user.getId());
        req.setAttribute("games", games);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}

package servlets;

import interfaces.GameService;
import models.Game;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class MainServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        gameService = (GameService) servletContext.getAttribute("gameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> result = gameService.getAll();
        req.setAttribute("games", result);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

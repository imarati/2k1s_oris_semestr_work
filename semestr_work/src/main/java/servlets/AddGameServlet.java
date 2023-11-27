package servlets;

import interfaces.FileService;
import interfaces.GameService;
import models.Game;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/addGame")
@MultipartConfig
public class AddGameServlet extends HttpServlet {
    FileService fileService;
    GameService gameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        fileService =(FileService) servletContext.getAttribute("fileService");
        gameService = (GameService) servletContext.getAttribute("gameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/addGame.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String review = req.getParameter("review");

        Part part = req.getPart("image");
        long fileId = fileService.saveFileToStorage(
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());

        Game game = Game.builder()
                .name(name)
                .review(review)
                .fileId(fileId)
                .build();

        gameService.addGame(game);

        resp.sendRedirect("/addGame");
    }
}

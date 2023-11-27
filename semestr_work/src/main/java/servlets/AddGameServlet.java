package servlets;

import interfaces.FileService;
import interfaces.GameService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/addGame")
public class AddGameServlet extends HttpServlet {
    FileService fileService;
    GameService gameService;
}

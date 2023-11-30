package servlets;

import interfaces.CartSevice;
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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartSevice cartSevice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        cartSevice = (CartSevice) servletContext.getAttribute("cartSevice");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> games = cartSevice.getFromCart();
        req.setAttribute("games", games);
        req.getRequestDispatcher("/jsp/Cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");

        cartSevice.buy(user.getId());

        resp.sendRedirect("/");
    }
}

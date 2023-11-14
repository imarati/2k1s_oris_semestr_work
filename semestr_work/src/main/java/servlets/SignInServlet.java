package servlets;

import dto.SignInForm;
import exceptions.InvalidEmailException;
import interfaces.SignInService;
import services.SignInServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        signInService = (SignInService) servletContext.getAttribute("signINService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/signIn.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        SignInForm signInForm = SignInForm.builder()
                .email(email)
                .password(password)
                .build();

        try {
            signInService.signIn(signInForm);

            resp.sendRedirect("/");
        }
        catch (Exception e) {
            req.getRequestDispatcher("/html/signIn.html").forward(req, resp);
        }
    }
}

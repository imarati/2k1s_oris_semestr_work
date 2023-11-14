package servlets;

import dto.SignUpForm;
import interfaces.SignUpService;
import services.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        signUpService = (SignUpService) servletContext.getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/signUp.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        SignUpForm signUpForm = SignUpForm.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build();

        signUpService.signUp(signUpForm);

        resp.sendRedirect("/");
    }
}

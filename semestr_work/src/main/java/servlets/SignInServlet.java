package servlets;

import dto.SignInForm;
import exceptions.InvalidEmailException;
import interfaces.RoleService;
import interfaces.SignInService;
import models.User;
import services.SignInServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;
    private RoleService roleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        signInService = (SignInService) servletContext.getAttribute("signInService");
        roleService = (RoleService) servletContext.getAttribute("roleService");
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
            User user = signInService.signIn(signInForm);
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authenticated", true);
            httpSession.setAttribute("user", user);

            String role = roleService.getRole(user.getId());

            httpSession.setAttribute("role", role);

            resp.sendRedirect("/");
        }
        catch (Exception e) {
            req.getRequestDispatcher("/html/signIn.html").forward(req, resp);
        }
    }
}

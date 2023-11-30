package servlets;

import dto.SignUpForm;
import interfaces.AddAdminService;
import interfaces.RoleService;
import models.User;
import services.AddAdminServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addAdmin")
public class AddAdminServlet extends HttpServlet {
    private AddAdminService addAdminService;
    private RoleService roleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        addAdminService = (AddAdminServiceImpl) servletContext.getAttribute("addAdminService");
        roleService = (RoleService) servletContext.getAttribute("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> admins = roleService.getUsers(2);
        req.setAttribute("admins", admins);
        req.getRequestDispatcher("/jsp/addAdmin.jsp").forward(req, resp);
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

        addAdminService.addAdmin(signUpForm);

        resp.sendRedirect("/");
    }
}

package filters;


import dto.SignUpForm;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");

        System.out.println(request.getRequestURI());

        Boolean isAuthenticated = false;
        Boolean isAdmin = false;
        Boolean sessionExists = session != null;
        Boolean isSignInPage = request.getRequestURI().equals("/signIn");
        Boolean isSignUpPage = request.getRequestURI().equals("/signUp");
        Boolean isMainPage = request.getRequestURI().equals("/");
        Boolean isGamePage = request.getRequestURI().equals("/game");
        Boolean isAddAdminPage = request.getRequestURI().equals("/addAdmin");
        Boolean isAddGamePage = request.getRequestURI().equals("/addGame");
        Boolean isFilesUploaded = request.getRequestURI().equals("/uploaded/files");

        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
            if (role == null) {
                isAdmin = false;
            }
            else {
                isAdmin = role.equals("admin");
            }
        }


        if((!isAuthenticated && (isSignUpPage || isSignInPage || isMainPage || isGamePage || isFilesUploaded))
                || (isAuthenticated && !isAdmin && !(isAddAdminPage || isAddGamePage))
                || (isAuthenticated && isAdmin)) {
            filterChain.doFilter(request, response);
        }
        else if (isAuthenticated && !isAdmin && (isAddAdminPage || isAddGamePage)) {
            response.sendRedirect("/");
        }
        else {
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}

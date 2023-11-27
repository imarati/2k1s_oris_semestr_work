package listener;

import interfaces.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.FileRepositoryImpl;
import repositories.GameRepositoryImpl;
import repositories.RoleRepositoryImpl;
import repositories.UserRepositoryImpl;
import services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DataBaseServletContextListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semester_work";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        ServletContext servletContext = sce.getServletContext();

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        servletContext.setAttribute("userRepository", userRepository);
        RoleRepository roleRepository = new RoleRepositoryImpl(dataSource);
        servletContext.setAttribute("roleRepository", roleRepository);
        FileRepository fileRepository = new FileRepositoryImpl(dataSource);
        servletContext.setAttribute("fileRepository", fileRepository);
        GameRepository gameRepository = new GameRepositoryImpl(dataSource);
        servletContext.setAttribute("gameRepository", gameRepository);
        SignUpService signUpService = new SignUpServiceImpl(userRepository);
        servletContext.setAttribute("signUpService", signUpService);
        SignInService signInService = new SignInServiceImpl(userRepository, roleRepository);
        servletContext.setAttribute("signInService", signInService);
        AddAdminService addAdminService = new AddAdminServiceImpl(userRepository);
        servletContext.setAttribute("addAdminService", addAdminService);
        RoleService roleService = new RoleServiceImpl(roleRepository);
        servletContext.setAttribute("roleService", roleService);
        FileService fileService = new FileServiceImpl(fileRepository);
        servletContext.setAttribute("fileService", fileService);
        GameService gameService = new GameServiceImpl(gameRepository);
        servletContext.setAttribute("gameService", gameService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

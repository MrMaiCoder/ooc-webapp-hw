package servlets;

import services.DatabaseService;
import services.SecurityService;
import services.UserService;

import javax.servlet.http.HttpServlet;

public abstract class AbstractRoutableHttpServlet extends HttpServlet implements Routable {

    protected SecurityService securityService;
    protected DatabaseService databaseService;
    protected UserService userService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setSDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.DatabaseService;
import io.muzoo.ooc.webapp.basic.security.SecurityService;
import io.muzoo.ooc.webapp.basic.security.UserService;

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

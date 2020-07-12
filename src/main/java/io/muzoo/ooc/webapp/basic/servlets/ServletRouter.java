package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.DatabaseService;
import io.muzoo.ooc.webapp.basic.security.SecurityService;
import io.muzoo.ooc.webapp.basic.security.User;
import io.muzoo.ooc.webapp.basic.security.UserService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServletRouter {

    private final List<Class<? extends AbstractRoutableHttpServlet>> servletClasses = new ArrayList<>();
    
    {
        servletClasses.add(HomeServlet.class);
        servletClasses.add(LoginServlet.class);
        servletClasses.add(LogoutServlet.class);
        servletClasses.add(EditServlet.class);
        servletClasses.add(UserListServlet.class);
        servletClasses.add(AddUserServlet.class);
    }

    public void init(Context ctx) throws SQLException, ClassNotFoundException {
        UserService userService = new UserService();
        SecurityService securityService = new SecurityService();
        DatabaseService databaseService = new DatabaseService();

        userService.setDatabaseService(databaseService);
        securityService.setUserService(userService);
        securityService.setDatabaseService(databaseService);
        databaseService.initDatabase();

        for (Class<? extends AbstractRoutableHttpServlet> servletClass: servletClasses) {
            try {
                AbstractRoutableHttpServlet httpServlet = servletClass.newInstance();
                httpServlet.setSecurityService(securityService);
                httpServlet.setSDatabaseService(databaseService);
                httpServlet.setUserService(userService);
                Tomcat.addServlet(ctx, servletClass.getSimpleName(), httpServlet);
                ctx.addServletMapping(httpServlet.getPattern(), servletClass.getSimpleName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }


}

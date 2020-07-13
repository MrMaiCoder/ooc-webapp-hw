import servlets.ServletRouter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.sql.SQLException;

public class Webapp {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);

        File docBase = new File("src/main/webapp/");
        docBase.mkdirs();

        try {
            Context ctx = tomcat.addWebapp("", docBase.getAbsolutePath());

            ServletRouter servletRouter = new ServletRouter();
            try {
                servletRouter.init(ctx);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }


    }
}

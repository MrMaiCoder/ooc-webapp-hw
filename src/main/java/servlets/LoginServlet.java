package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends AbstractRoutableHttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        try {
            if (securityService.login(request)){
                response.sendRedirect("/");
            } else {
                error = "Username or password incorrect. Please try again.";

                request.setAttribute("error", error);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
                requestDispatcher.include(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getPattern() {
        return "/login";
    }
}

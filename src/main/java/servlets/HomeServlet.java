package servlets;

import services.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HomeServlet extends AbstractRoutableHttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (securityService.isAuthorized(request)){
                String username = securityService.getCurrentUsername(request);
                User user = userService.getUser(username);
                request.setAttribute("username", username);
                request.setAttribute("firstName", user.getFirstName());
                request.setAttribute("lastName", user.getLastName());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
                requestDispatcher.include(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("user_list") != null) {
            response.sendRedirect("/user_list");
        }
        if (request.getParameter("logout") != null) {
            response.sendRedirect("/logout");
        }
    }

    @Override
    public String getPattern() {
        return "/index.jsp";
    }
}

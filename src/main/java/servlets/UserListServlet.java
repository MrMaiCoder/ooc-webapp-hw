package servlets;

import services.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserListServlet extends AbstractRoutableHttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if (securityService.isAuthorized(request)) {
                String username = securityService.getCurrentUsername(request);
                ArrayList<User> usersList = userService.getAllUsers();
                request.setAttribute("username", username);
                request.setAttribute("usersList", usersList);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/user_list.jsp");
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
        if (request.getParameter("removeUser") != null) {
            if (request.getParameter("usernameString") != null) {
                try {
                    String username = request.getParameter("usernameString");
                    userService.deleteUser(username);
                    doGet(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (request.getParameter("editUserPage") != null) {
            if (request.getParameter("usernameString") != null) {
                String username = request.getParameter("usernameString");
                request.getSession().setAttribute("toEdit", username);
                response.sendRedirect("/edit_user");
            }

        }
        if (request.getParameter("addUser") != null) {
            response.sendRedirect("/add_user");
        }

        if (request.getParameter("back") != null) {
            response.sendRedirect("/");
        }
        if (request.getParameter("logout") != null) {
            response.sendRedirect("/logout");
        }
    }

    @Override
    public String getPattern() {
        return "/user_list";
    }
}

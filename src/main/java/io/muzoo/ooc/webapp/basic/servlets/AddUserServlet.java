package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (securityService.isAuthorized(request)) {
                String username = securityService.getCurrentUsername(request);
                request.setAttribute("username", username);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/add_user.jsp");
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
        String error;
        if (request.getParameter("addUser") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String confirmPassword = request.getParameter("confirmPassword");
            String message;
            try {
                if (userService.userExists(username)) {
                    error = "Username exists. Please choose a new one.";
                    request.setAttribute("error", error);
                } else if (!password.equals(confirmPassword)) {
                    error = "Passwords didn't match. Please Try again.";
                    request.setAttribute("error", error);
                } else {
                    message = "User added!";
                    request.setAttribute("message", message);
                    userService.createUser(username, password, firstName, lastName);
                    doGet(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("back") != null) {
            response.sendRedirect("/user_list");
        }
    }

    @Override
    public String getPattern() {
        return "/add_user";
    }
}

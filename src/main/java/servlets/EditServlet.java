package servlets;

import services.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (securityService.isAuthorized(request)) {
                String username = (String) request.getSession().getAttribute("toEdit");
                User user = userService.getUser(username);
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/edit_user.jsp");
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
        String message;
        if (request.getParameter("editUser") != null) {
            String username = (String) request.getSession().getAttribute("toEdit");
            try {
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

                if (password.equals(confirmPassword)) {
                    if (!request.getParameter("newUsername").isEmpty()) {
                        String newUsername = request.getParameter("newUsername");
                        userService.updateUser("username", newUsername, username);
                        message = "Username edited";
                        request.setAttribute("usernameMessage", message);
                        request.getSession().setAttribute("toEdit", newUsername);
                    }
                    if (!request.getParameter("newPassword").isEmpty()){
                        String newPassword = request.getParameter("newPassword");
                        message = "Password edited";
                        request.setAttribute("passwordMessage", message);
                        userService.updateUser("password", newPassword, username);
                    }
                    if (!request.getParameter("firstName").isEmpty()) {
                        String firstName = request.getParameter("firstName");
                        message = "First name edited";
                        request.setAttribute("firstNameMessage", message);
                        userService.updateUser("first_name", firstName, username);
                    }
                    if (!request.getParameter("lastName").isEmpty()) {
                        String lastName = request.getParameter("lastName");
                        message = "Last name edited";
                        request.setAttribute("lastNameMessage", message);
                        userService.updateUser("last_name", lastName, username);
                    }
                } else {
                    error = "Passwords didn't match. Please Try again.";
                    request.setAttribute("error", error);
                }
                doGet(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("back") != null) {
            response.sendRedirect("/user_list");
        }
        if (request.getParameter("logout") != null) {
            response.sendRedirect("/logout");
        }
    }

    @Override
    public String getPattern() {
        return "/edit_user";
    }
}

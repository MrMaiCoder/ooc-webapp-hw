package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class SecurityService {
    // Authentication-related services

    private DatabaseService databaseService;
    private UserService userService;

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public String getCurrentUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object usernameObject = session.getAttribute("username");
        return (String) usernameObject;
    }


    public boolean isAuthorized(HttpServletRequest request) throws SQLException {
        String username = getCurrentUsername(request);
        return userService.userExists(username);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();

    }

    public boolean login(HttpServletRequest request) throws SQLException {
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ResultSet resultSet = databaseService.selectByUsername(username);
        if (resultSet.next()) {
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            if (BCrypt.checkpw(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                return true;
            }
        }
        return false;
    }
}

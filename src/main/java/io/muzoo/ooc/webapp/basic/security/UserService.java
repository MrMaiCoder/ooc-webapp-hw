package io.muzoo.ooc.webapp.basic.security;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    // User-related services

    private DatabaseService databaseService;

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    public boolean userExists(String username) throws SQLException {
        ResultSet resultSet = databaseService.selectByUsername(username);
        return resultSet.next();

    }

    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> usersList = new ArrayList<>();
        ResultSet resultSet = databaseService.selectAll();
        while(resultSet.next()) {
            String username = resultSet.getString("username");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            User user = new User();
            user.setUsername(username);
            user.setFirstName(first_name);
            user.setLastName(last_name);
            usersList.add(user);
        }
        return usersList;
    }

    public User getUser(String username) throws SQLException {
        User user = new User();
        ResultSet resultSet = databaseService.selectByUsername(username);
        resultSet.next();
        user.setUsername(username);
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        return user;
    }

    public void createUser(String username, String password, String firstName, String lastName) throws SQLException {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            databaseService.insertUserQuery(username, hashedPassword, firstName, lastName);
    }

    public void deleteUser(String username) throws SQLException {
        databaseService.deleteUserQuery(username);
    }

    public void updateUser(String updateType, String updatedData, String username) throws SQLException {
        if (updateType.equals("password")){
            updatedData = BCrypt.hashpw(updatedData,BCrypt.gensalt());
        }
        databaseService.updateUserQuery(updateType, updatedData, username);
    }


}

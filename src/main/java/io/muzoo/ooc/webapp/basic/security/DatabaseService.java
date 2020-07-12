package io.muzoo.ooc.webapp.basic.security;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class DatabaseService {
    // Database-related services

    private Statement statement;

    public void initDatabase() throws SQLException, ClassNotFoundException {
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String jdbcURL = "jdbc:mysql://localhost/ooc_database?user=mai&password=02536487";
        Class.forName(jdbcDriver);
        Connection connection = DriverManager.getConnection(jdbcURL);
        statement = connection.createStatement();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables = databaseMetaData.getTables(null, null, "users_table", null);
        if (!tables.next()) {
            statement.execute("create table users_table(username varchar(255) not null , " +
                    "password varchar(255) not null, " +
                    "first_name varchar(255), " +
                    "last_name varchar(255), " +
                    "primary key (username))");
            String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ooc_database.users_table values ('admin','" + hashedPassword + "','Phairat','Lin')");
            preparedStatement.execute();
        }
    }

    public ResultSet selectAll() throws SQLException {
        return statement.executeQuery("select * from users_table");
    }

    public ResultSet selectByUsername(String username) throws SQLException {
        return statement.executeQuery("select * from users_table where username ='" + username + "'");
    }

    public void insertUserQuery(String username, String password, String firstName, String lastName) throws SQLException {
        if (firstName == null && lastName == null) {
            statement.execute("insert into users_table(username, password) values ( '" + username + "', '" + password + "')");
        } else if (firstName != null && lastName == null) {
            statement.execute("insert into users_table(username, password, first_name) " +
                    "values ( '" + username + "', '" + password + "', '" + firstName + "')");
        } else if (firstName == null && lastName != null) {
            statement.execute("insert into users_table(username, password, last_name) " +
                    "values ( '" + username + "', '" + password + "', '" + lastName + "')");
        } else if (firstName != null && lastName != null){
            statement.execute("insert into users_table(username, password, first_name, last_name) " +
                    "values ( '" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "')");
        }
    }

    public void deleteUserQuery(String username) throws SQLException {
        statement.execute("delete from users_table where username ='" + username + "'");
    }

    public void updateUserQuery(String updateType, String updatedData, String username) throws SQLException {
        statement.execute("update users_table " +
                "set " + updateType + " = '"  + updatedData + "' " +
                "where username = '" + username + "'");
    }


}

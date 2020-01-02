package Sinh.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Sinh.Model.User;

public class UserDB {

    public static List<User> getUsers() {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String userName = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            connection = (Connection) DriverManager.getConnection(url, userName, password);
            String sql = "SELECT * FROM user ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet userResultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (userResultSet.next()) {
                String email = userResultSet.getString("Email");
                String innerPassword = userResultSet.getString("Password");
                String firstName = userResultSet.getString("FirstName");
                String lastName = userResultSet.getString("LastName");
                User user = new User(email, innerPassword, firstName, lastName);
                users.add(user);
            }
            return users;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public static int insertUser(User user) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String userName = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            connection = (Connection) DriverManager.getConnection(url, userName, password);
            String sql = "INSERT INTO userdb.user (Email, Password, FirstName, LastName)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return 0;
    }

    public static int editUser(User user) {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String userName = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            connection = (Connection) DriverManager.getConnection(url, userName, password);
            String sql = "update userdb.user set "
                    + " Email = ?, "
                    + " Password = ?, "
                    + " firstName = ?, "
                    + " lastName = ? "
                    + " where Email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static int removeUser(User user) {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String userName = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            connection = (Connection) DriverManager.getConnection(url, userName, password);
            String sql = "delete from userdb.user "
                    + " where Email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static User getUser(String email) {
        return new User();
    }
}

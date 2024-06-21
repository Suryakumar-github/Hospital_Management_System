
package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection connection = null;
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Management", "root", "");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}


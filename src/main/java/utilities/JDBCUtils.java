package utilities;

import java.sql.*;

/**
 * JDBC Utilities using Druid
 */
public class JDBCUtils {
    /**
     * getConnection
     *
     * @return a Connection object that can be used to connect to the database
     * @throws SQLException
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/R01_USERAUTH", "root", "rootroot");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void cleanUp(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void cleanup(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

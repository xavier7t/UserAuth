package model;

import utilities.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for the Credential table
 */
public class CredentialDAO {
    /**
     * login method
     *
     * @param formSubmitted A credential object that contains the username and password only
     * @return A credential object that contains the all properties of the user queried from the database
     */
    public static Credential login(Credential formSubmitted) {
        String sql = "SELECT * FROM ZCREDENTIAL WHERE USERNAME =? AND PASSWORD =?";
        Credential credential = new Credential();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, formSubmitted.getUsername());
            preparedStatement.setString(2, formSubmitted.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                credential.setId(resultSet.getInt("ID"));
                credential.setUsername(resultSet.getString("USERNAME"));
                credential.setPassword(resultSet.getString("PASSWORD"));
            } else {
                return null;
            }
            return credential;
        } catch (SQLException e) {
            return null;
        }
    }
}

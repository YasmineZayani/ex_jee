package tn.iit.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.iit.user.bean.User;
import tn.iit.user.bean.Authorization;

public class AuthorizationDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/miniprj?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private String jdbcDriver = "com.mysql.jdbc.Driver";

    private static final String INSERT_AUTHORIZATION_SQL = "INSERT INTO authorizations (id, user_id, current_date, authorization_date, maximum_hours) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_AUTHORIZATIONS_BY_USER_SQL = "SELECT * FROM authorizations WHERE user_id = ?";
    private static final String UPDATE_MAXIMUM_HOURS_SQL = "UPDATE authorizations SET maximum_hours = ? WHERE id = ?";

    private UserDao userDao;

    public AuthorizationDAO() {
        userDao = new UserDao();
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addAuthorization(Authorization authorization) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHORIZATION_SQL)) {
            preparedStatement.setInt(1, authorization.getId());
            preparedStatement.setInt(2, authorization.getUser().getId());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(authorization.getCurrentDate().getTime()));
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(authorization.getAuthorizationDate().getTime()));
            preparedStatement.setInt(5, authorization.getMaximumHours());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<Authorization> getAuthorizationsByUser(int userId) {
        List<Authorization> authorizations = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHORIZATIONS_BY_USER_SQL)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date currentDate = rs.getTimestamp("current_date");
                Date authorizationDate = rs.getTimestamp("authorization_date");
                int maximumHours = rs.getInt("maximum_hours");

                User user = userDao.selectUser(userId);
                Authorization authorization = new Authorization(id, user, currentDate, authorizationDate, maximumHours);
                authorizations.add(authorization);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return authorizations;
    }

    public void updateMaximumHours(int authorizationId, int maximumHours) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MAXIMUM_HOURS_SQL)) {
            preparedStatement.setInt(1, maximumHours);
            preparedStatement.setInt(2, authorizationId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

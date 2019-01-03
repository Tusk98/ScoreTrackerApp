import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String CONN_STRING = "jdbc:mysql://localhost/scoretracker";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    public static void processException(SQLException e) {
        System.err.println("Error Message: " + e.getMessage());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Error Message: " + e.getSQLState());
    }
}

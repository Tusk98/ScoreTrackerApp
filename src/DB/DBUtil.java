package DB;

import Tables.MembershipList;

import java.sql.*;

public class DBUtil {
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String CONN_STRING = "jdbc:mysql://localhost/scoretracker";

    public static boolean checkCredentials(String username, String password){
        boolean matched = false;

        // Empty login value, just reject
        if (username == null || password == null) {
            return false;
        }

        try (
                //try-within block, will auto close connections when done
                Connection conn = getConnection();
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM membershiplist;")
        ){

            // Loop through table looking for matching username and password
            if (rs.next()) {
                System.out.println("INSIDE");
                if (username.equals(new String(rs.getString("User_Name")))) {
                    System.out.println("USERNAME: " + username);
                    System.out.println(rs.getString("User_Name"));

                    if (password.equals(new String(rs.getString("Password")))) {
                        System.out.println("PASSWORD: "+ password);
                        System.out.println(rs.getString("Password"));
                        matched = true;
                    }
                }
            } else {
                matched = false;
            }
            System.out.println("OUTSIDE");


        } catch (SQLException e) {
            processException(e);
        }

        return matched;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    public static void processException(SQLException e) {
        System.err.println("Error Message: " + e.getMessage());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Error Message: " + e.getSQLState());
    }

}

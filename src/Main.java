import java.sql.*;

public class Main {


    public static void main (String [] args) throws SQLException {


        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT User_ID FROM membershiplist");
            ){

            // Move cursor to end of resultset
            rs.last();
            System.out.println("Number of rows: " + rs.getRow());


            System.out.println("Connected.");

        } catch (SQLException e) {
            DBUtil.processException(e);
        }

    }
}

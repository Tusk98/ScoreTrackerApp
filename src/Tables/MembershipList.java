package Tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MembershipList {

    public static void displayData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Member " + rs.getInt("User_ID") + ": ");
            buffer.append(rs.getString("User_Name"));
            System.out.println(buffer.toString());
            System.out.println("Hello");
        }
    }

    public static void getUsers() {


    }

}

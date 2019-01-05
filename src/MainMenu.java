import Tables.MembershipList;


import java.awt.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;


public class MainMenu {

    private JFrame mainOptionMenu;

    public MainMenu() {
        initialize();
    }

    public static void main (String [] args) throws SQLException {
        MainMenu.startUpMenu();
    }

    public static void startUpMenu() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainMenu window = new MainMenu();
                    window.mainOptionMenu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initialize() {
        mainOptionMenu = new JFrame();
        mainOptionMenu.setTitle("ScoreKeeper");
        mainOptionMenu.setBounds(100, 100, 500, 300);
        mainOptionMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainOptionMenu.getContentPane().setLayout(null);

        JLabel label = new JLabel("Main menu:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 22));
        label.setBounds(15, 16, 415, 33);
        mainOptionMenu.getContentPane().add(label);
    }

    public static void dbConnect() {
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM membershiplist");
        ){


            MembershipList.displayData(rs);

            System.out.println("Connected.");

        } catch (SQLException e) {
            DBUtil.processException(e);
        }
    }

}

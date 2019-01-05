package Frames;

import DB.DBUtil;
import Tables.MembershipList;

import java.awt.*;
import java.sql.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginFrame extends JPanel{

    private JFrame mainOptionMenu;
    private JFrame usersListMenu;
    private JButton meme;

    public LoginFrame() {
        initialize();
    }

    public static void beginApp() throws SQLException {
        LoginFrame.startUpMenu();
    }

    public static void startUpMenu() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginFrame window = new LoginFrame();
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
        mainOptionMenu.add(label);

        JTextField txtUsername = new JTextField("Username");
        txtUsername.setBounds(54,8,166,26);
        mainOptionMenu.getContentPane().add(txtUsername);

        // Button to go to new JFrame to view the list of users
        JButton usersListButton = new JButton("View Users");
        usersListButton.setFont(new Font("Aria", Font.PLAIN, 24));
        usersListButton.setBounds(50, 60, 400, 30);
        usersListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainOptionMenu.dispose();
                new MembershipFrame();


            }
        });
        mainOptionMenu.getContentPane().add(usersListButton);

        //View the list of users button







        /* Code to create another pane
         *
         Double Click the Login Button in the NETBEANS or add the Event Listener on Click Event (ActionListener)

         btnLogin.addActionListener(new ActionListener()
         {
         public void actionPerformed(ActionEvent e) {
         this.setVisible(false);
         new FrmMain().setVisible(true); // Main Form to show after the Login Form..
         }
         });
         */


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

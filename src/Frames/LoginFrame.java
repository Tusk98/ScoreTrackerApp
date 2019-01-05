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
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    String username = null;
    String password = null;

    public LoginFrame() {
        initialize();
    }

    public static void main(String[] args) throws SQLException {
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
        mainOptionMenu.setBounds(100, 100, 650, 500);
        mainOptionMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainOptionMenu.getContentPane().setLayout(null);

        Font typeFont = new Font("SansSerif", Font.BOLD, 20);

        JLabel label = new JLabel("Login menu:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(15, 16, 415, 33);
        mainOptionMenu.add(label);

        //Username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        userLabel.setBounds(50, 66, 415, 33);
        mainOptionMenu.add(userLabel);

        //Username text field
        txtUsername = new JTextField(50);
        txtUsername.setBounds(50,100,500,35);
        txtUsername.setFont(typeFont);
        mainOptionMenu.getContentPane().add(txtUsername);

        //Password text field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        passLabel.setBounds(50, 166, 415, 33);
        mainOptionMenu.add(passLabel);

        //Password text field
        txtPassword = new JPasswordField(50);
        txtPassword.setBounds(50, 200, 500,35);
        mainOptionMenu.getContentPane().add(txtPassword);


        // Login button
        JButton mainMenuBtn = new JButton("Login");
        mainMenuBtn.setFont(new Font("Aria", Font.PLAIN, 24));
        mainMenuBtn.setBounds(250, 300, 100, 30);
        mainMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Set username variables to what's in fields, check DB for matching words
                getUsername();
                getPassword();

                // Query DB for matching membership
                boolean matched = DBUtil.checkCredentials(username, password);

                // Show message for successful or failed login
                if (matched) {
                    JOptionPane.showMessageDialog(mainOptionMenu,
                            "Successful Login",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainOptionMenu.dispose();

                } else {
                    JOptionPane.showMessageDialog(mainOptionMenu,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText("");
                }
            }
        });
        mainOptionMenu.getContentPane().add(mainMenuBtn);

    }

    private void getUsername() {
        username = txtUsername.getText().trim();
    }

    private void getPassword() {
        password = new String(txtPassword.getPassword());
    }


}

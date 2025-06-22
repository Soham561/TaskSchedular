package com.Projects.TaskSchedular.TaskScheduler;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends Frontend {
    private final String username = "Soham$oham";
    private final String passwd = "Soham@123";

    public LoginPage() {
        this.setLayout(null);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(50, 50, 50));
        loginPanel.setBounds(0, 0, 350, 200);

        JLabel welcomeLabel = new JLabel("Task Scheduler Login");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(60, 10, 250, 30);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(30, 50, 100, 25);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(130, 50, 170, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(30, 90, 100, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(130, 90, 170, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(60, 140, 100, 30);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(180, 140, 100, 30);

        loginPanel.add(welcomeLabel);
        loginPanel.add(userLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(cancelButton);

        this.add(loginPanel);

        loginButton.addActionListener(e -> {
            String enteredUser = usernameField.getText();
            String enteredPass = new String(passwordField.getPassword());

            if (enteredUser.equals(username) && enteredPass.equals(passwd)) {
                JOptionPane.showMessageDialog(this, "Welcome to Task Scheduler!");
                this.dispose();
                Backend_JDBC backendJdbc = new Backend_JDBC();
                try {
                    backendJdbc.initialize();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> System.exit(0));

        this.setTitle("Login - Task Scheduler");
        this.setSize(360, 220);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}

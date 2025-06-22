package com.Projects.TaskSchedular.TaskScheduler;
import javax.swing.*;
import java.awt.*;

public class Main extends Backend_JDBC{
    public static void main(String[] args) {
        try {
            // Create JFrame
            JFrame frame = new JFrame("Loading...");
            frame.setSize(250, 160);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center on screen
            frame.getContentPane().setBackground(new Color(245, 245, 245)); // Light background

            // Heading Label
            JLabel heading = new JLabel("Welcome to Task Scheduler", JLabel.CENTER);
            heading.setBounds(10, 10, 220, 20);
            heading.setFont(new Font("Arial", Font.BOLD, 14));
            heading.setForeground(new Color(60, 63, 65));
            frame.add(heading);

            // Subtext Label
            JLabel subtext = new JLabel("Initializing, please wait...", JLabel.CENTER);
            subtext.setBounds(10, 30, 220, 20);
            subtext.setFont(new Font("Arial", Font.PLAIN, 11));
            subtext.setForeground(new Color(100, 100, 100));
            frame.add(subtext);

            // Progress Bar
            JProgressBar jProgressBar = new JProgressBar();
            jProgressBar.setMaximum(100);
            jProgressBar.setMinimum(0);
            jProgressBar.setValue(0);
            jProgressBar.setStringPainted(true);
            jProgressBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
            jProgressBar.setForeground(new Color(76, 175, 80)); // Stylish green
            jProgressBar.setBackground(new Color(224, 224, 224));
            jProgressBar.setBounds(20, 70, 200, 25);
            frame.add(jProgressBar);

            // Optional Footer Label
            JLabel footer = new JLabel("© 2025 Task Manager Inc.", JLabel.CENTER);
            footer.setBounds(10, 100, 220, 20);
            footer.setFont(new Font("Tahoma", Font.ITALIC, 10));
            footer.setForeground(new Color(130, 130, 130));
            frame.add(footer);

            frame.setVisible(true);

            // Animate Progress
            for (int i = 0; i <= 100; i++) {
                jProgressBar.setValue(i);
                Thread.sleep(40); // Animation speed
            }

            frame.dispose(); // Close after loading
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         LoginPage loginPage = new LoginPage();
        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
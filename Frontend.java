package com.Projects.TaskSchedular.TaskScheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Frontend extends JFrame {

    public JButton jButton, jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7;
    public DefaultTableModel defaultTableModel;
    public JTable table;
    public JComboBox jComboBox;

    public void initiate() {
        this.setTitle("Task Scheduler");
        this.setLayout(new BorderLayout(8, 8));

        Font headerFont = new Font("Segoe UI", Font.BOLD, 28);
        Font subHeaderFont = new Font("Segoe UI", Font.PLAIN, 18);
        Font btnFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Header
        JPanel panel0 = new JPanel();
        panel0.setBackground(new Color(50, 63, 95));
        JLabel label = new JLabel("Welcome to Task Scheduler");
        label.setFont(headerFont);
        label.setForeground(Color.WHITE);
        panel0.add(label);
        this.add(panel0, BorderLayout.NORTH);

        // Left Panel (Table)
        JPanel panel1 = new JPanel(new BorderLayout(10, 10));
        panel1.setBackground(new Color(245, 245, 245));
        this.add(panel1, BorderLayout.WEST);

        String[] columns = {"Sr_no", "Schedule_Name", "Schedule_Date", "Schedule_Time", "Repeat_Or_Not"};
        defaultTableModel = new DefaultTableModel(columns, 0);
        table = new JTable(defaultTableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setBackground(new Color(245, 245, 245));

        jButton = createButton("Save Changes", new Color(76, 175, 80));
        jButton1 = createButton("Revert", new Color(255, 167, 38));
        jButton2 = createButton("Delete A Record", new Color(244, 67, 54));
        jButton3 = createButton("Show current Status", new Color(33, 150, 243));

        panel2.add(jButton);
        panel2.add(jButton1);
        panel2.add(jButton2);
        panel2.add(jButton3);
        panel1.add(panel2, BorderLayout.SOUTH);

        // Footer
        JPanel panel4 = new JPanel();
        panel4.setBackground(new Color(33, 33, 33));
        JLabel label1 = new JLabel("© Made By Soham ");
        label1.setFont(subHeaderFont);
        label1.setForeground(new Color(255, 255, 255));
        panel4.add(label1);
        this.add(panel4, BorderLayout.SOUTH);

        // Table Heading
        JPanel panel3 = new JPanel();
        panel3.setBackground(new Color(245, 245, 245));
        JLabel label2 = new JLabel("Your Tasks and Their Schedules");
        label2.setFont(subHeaderFont);
        label2.setForeground(new Color(0, 120, 215));
        panel3.add(label2);
        panel1.add(panel3, BorderLayout.NORTH);

        // Center (Main Content Panel)
        JPanel panel5 = new JPanel(new BorderLayout(10, 10));
        this.add(panel5, BorderLayout.CENTER);

        // Menu Bar Heading
        JPanel panel6 = new JPanel();
        panel6.setBackground(new Color(39, 55, 70));
        JLabel label3 = new JLabel("Menus");
        label3.setFont(subHeaderFont);
        label3.setForeground(Color.WHITE);
        panel6.add(label3);
        panel5.add(panel6, BorderLayout.NORTH);

        // Buttons Panel
        JPanel panel7 = new JPanel();
        panel7.setBackground(new Color(245, 245, 245));
        jButton4 = createButton("NEW Task", new Color(3, 218, 198));
        jButton5 = createButton("Update Task", new Color(100, 221, 23));
        jButton6 = createButton("Timer", new Color(255, 241, 118));
        jButton7 = createButton("Schedule", new Color(0, 188, 212));
        panel7.add(jButton4);
        panel7.add(jButton5);
        panel7.add(jButton6);
        panel7.add(jButton7);
        panel5.add(panel7, BorderLayout.SOUTH);

        // ComboBox Panel
        JPanel panel8 = new JPanel(new BorderLayout());
        panel8.setBackground(Color.WHITE);
        jComboBox = new JComboBox();
        jComboBox.setBackground(new Color(233, 255, 170));
        jComboBox.setFont(btnFont);
        panel8.add(jComboBox, BorderLayout.NORTH);
        panel5.add(panel8, BorderLayout.CENTER);

        // Instruction Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 20, 350, 220);
        panel.setBackground(Color.WHITE);

        JLabel heading = new JLabel("@ INSTRUCTIONS");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(new Color(244, 67, 54));
        heading.setBounds(10, 200, 250, 30);
        panel.add(heading);

        String[] instructions = {
                "1. Click on 'Save Changes' after any edits",
                "2. Use 24-hour format for time",
                "3. Follow prompts carefully",
                "4. Always test before scheduling",
                "5. Keep things organized!"
        };

        int y = 240;
        for (String text : instructions) {
            JLabel lbl = new JLabel(text);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lbl.setForeground(new Color(120, 120, 120));
            lbl.setBounds(20, y, 500, 25);
            panel.add(lbl);
            y += 30;
        }

        panel8.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
        this.setSize(960, 710);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(250, 250, 250));
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        return btn;
    }
}

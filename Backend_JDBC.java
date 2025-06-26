package com.Projects.TaskSchedular.TaskScheduler;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;
import java.time.LocalTime;
import java.util.Arrays;

public class Backend_JDBC extends Frontend implements MouseInputListener {
    private final String userName = "root";
    private final String pass = "your Database pass";
    private final String url = "jdbc:mysql://localhost:3306/schedule_db";

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;

    private boolean isCommited = false;
    private String id = null;
    private int choice ;

    private void showAll() throws Exception {
        ResultSet rs = statement.executeQuery("SELECT * FROM schedule_tb");
        defaultTableModel.setRowCount(0);
        jComboBox.removeAllItems();
        while (rs.next()) {
            int id = rs.getInt("Sr_no");
            String name = rs.getString("Schedule_Name");
            String date = rs.getDate("Schedule_Date").toString();
            String time = rs.getTime("Schedule_Time").toString();
            boolean b = rs.getBoolean("Repeat_Or_Not");
            defaultTableModel.addRow(new Object[]{id, name, date, time, b});
            jComboBox.addItem(id + " : " + name + " : " + date + " : " + time);
        }
    }

    public void timerTime(long sec) throws Exception{
        long initial = 0;
        long sec1 = sec;
        JOptionPane.showMessageDialog(null,"Timer is Starting Please Press -> Ok !!");
        while (initial != sec){
            initial ++;
            sec1--;
            System.out.printf("\r" + "Remaining Time :" + (sec1) + " Seconds");
            Thread.sleep(1000);
        }
        File file = new File("C:/Users/SOHAM GAJANAN HAJARE/Downloads/polozhenie______s_l_o_w_e_d__(256k).wav");

            AudioInputStream audio = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);

            clip.start();

        int op = JOptionPane.showConfirmDialog(null,"Do you want to Stop??","Information",JOptionPane.OK_CANCEL_OPTION);
        if(op == JOptionPane.OK_OPTION){
            clip.stop();
        }
        if(!(jButton7.isEnabled())){
            jButton7.setEnabled(true);
        }

    }
    public void initialize() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        initiate();
        connection = DriverManager.getConnection(url, userName, pass);
        statement = connection.createStatement();
        connection.setAutoCommit(false);
        showAll();

        jButton.addActionListener(e -> {
            try {
                if (!isCommited) {
                    connection.commit();
                    isCommited = true;
                    JOptionPane.showMessageDialog(null, "Commited Successfully");
                    showAll();
                } else {
                    JOptionPane.showMessageDialog(null, "Already Commited!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        jButton1.addActionListener(e -> {
            if (isCommited)
                JOptionPane.showMessageDialog(null, "Already Commited");
            else {
                try {
                    connection.rollback();
                    JOptionPane.showMessageDialog(null, "RollBacked Successfully");
                    showAll();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        jButton2.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(null, "Want To Delete?", "Option", JOptionPane.OK_CANCEL_OPTION);
            if (op == JOptionPane.OK_OPTION) {
                choice = 0;
                JOptionPane.showMessageDialog(null, "So Now Select The Row To Delete");
                table.addMouseListener(this);
            } else if (op == JOptionPane.CANCEL_OPTION) {
                table.removeMouseListener(this);
            }
        });

        // ✅ Fixed Progress Button
        jButton3.addActionListener(e -> {
            try {
                if(!(connection.isClosed())){
                    JOptionPane.showMessageDialog(null,"All is Ok Still Connected to DataBase ✅");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        jButton4.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO ADD NEW TASK?", "INFORMATION", JOptionPane.OK_CANCEL_OPTION);
            boolean repeat = false;
            Time time = Time.valueOf(LocalTime.now());
            isCommited = false;
            String name;
            if (op == JOptionPane.OK_OPTION) {
                name = JOptionPane.showInputDialog("Enter the Name of You Schedule :");
                while (true) {
                    try {
                        time = Time.valueOf(JOptionPane.showInputDialog("Enter the Time in HH:mm:ss (24 hr Format):"));
                        break;
                    } catch (Exception e1) {
                        int opnt = JOptionPane.showConfirmDialog(null, "Enter Valid Time Again ? Or Cancel it ?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                        if (opnt == JOptionPane.CANCEL_OPTION) {
                            break;
                        }
                    }
                }
                int opn = JOptionPane.showConfirmDialog(null, "Do you Want to Repeat This Schedule?", "INFORMATION", JOptionPane.OK_CANCEL_OPTION);
                if (opn == JOptionPane.OK_OPTION) {
                    repeat = true;
                }

                if (name == null) {
                    JOptionPane.showMessageDialog(null, "Due to Name is Null Nothing Named Task Is Added :)");
                    try {
                        statement.executeUpdate("INSERT INTO schedule_tb VALUES ()");
                        JOptionPane.showMessageDialog(null, "Added Successfully");
                        showAll();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        preparedStatement = connection.prepareStatement("INSERT INTO schedule_tb (Schedule_Name,Schedule_Time,Repeat_Or_Not) VALUES (?,?,?)");
                        preparedStatement.setString(1, name);
                        preparedStatement.setTime(2, time);
                        preparedStatement.setBoolean(3, repeat);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Added Successfully");
                        showAll();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        jButton5.addActionListener(e->{
            isCommited  = false;
            int op = JOptionPane.showConfirmDialog(null,"Do You Want to Update An Existing Task??","Information",JOptionPane.OK_CANCEL_OPTION);
            if(op == JOptionPane.OK_OPTION){
                String [] str;
                try {
                    showAll();
                   str  = new String[table.getRowCount()];
                    int i = 0;
                    ResultSet resultSet = statement.executeQuery("SELECT Sr_no FROM schedule_tb");
                    while (resultSet.next()){
                        str[i] = String.valueOf(resultSet.getInt("Sr_no"));
                        i++;
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
               int input = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the Id To Update :\n"+ Arrays.toString(str)));
                int op1 = JOptionPane.showConfirmDialog(null,"Do You Want to Update An Existing Task??","Information",JOptionPane.OK_CANCEL_OPTION);
                if(op1 == JOptionPane.OK_OPTION) {
                    try {
                        ResultSet resultSet = null;
                        preparedStatement =  connection.prepareStatement("SELECT Schedule_Name FROM schedule_tb WHERE Sr_no = ?");
                        preparedStatement.setInt(1,input);
                        resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        String name = resultSet.getString("Schedule_Name");
                        preparedStatement =  connection.prepareStatement("SELECT Schedule_Time FROM schedule_tb WHERE Sr_no = ?");
                        preparedStatement.setInt(1,input);
                        resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        Time time = resultSet.getTime("Schedule_Time");
                        preparedStatement =  connection.prepareStatement("SELECT Repeat_Or_Not FROM schedule_tb WHERE Sr_no = ?");
                        preparedStatement.setInt(1,input);
                        resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        boolean b = resultSet.getBoolean("Repeat_Or_Not");
                        int which = Integer.parseInt(JOptionPane.showInputDialog(null,"What You Want To Update ?\n 1. Name : "+name+"\n"+"2. Time : "+time+"\n"+"3. Repeat : "+b+"\n Enter your Choice :\n"));
                        switch (which){
                            case 1:
                                name = JOptionPane.showInputDialog("Enter New Name :");
                                preparedStatement = connection.prepareStatement("UPDATE schedule_tb SET Schedule_Name = ? WHERE Sr_no = ?");
                                preparedStatement.setString(1,name);
                                preparedStatement.setInt(2,input);
                                preparedStatement.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Updated Successfully!");
                                showAll();
                                break;
                            case 2:
                                time = Time.valueOf(JOptionPane.showInputDialog("Enter New Time in HH:mm:ss :"));
                                preparedStatement = connection.prepareStatement("UPDATE schedule_tb SET Schedule_Time = ? WHERE Sr_no = ?");
                                preparedStatement.setTime(1,time);
                                preparedStatement.setInt(2,input);
                                preparedStatement.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Updated Successfully!");
                                showAll();
                                break;
                            case 3:
                                b = Boolean.parseBoolean(JOptionPane.showInputDialog("Enter New Repeat (true/false) :"));
                                preparedStatement = connection.prepareStatement("UPDATE schedule_tb SET Repeat_Or_Not = ? WHERE Sr_no = ?");
                                preparedStatement.setBoolean(1,b);
                                preparedStatement.setInt(2,input);
                                preparedStatement.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Updated Successfully!");
                                showAll();
                                break;

                            default:
                                JOptionPane.showMessageDialog(null,"Invalid Choice!!","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Something Went Wrong","Warning",JOptionPane.WARNING_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        jButton6.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(null,"Do You want to Set Timer ?","Information",JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                    try {
                        String format = (JOptionPane.showInputDialog(null, "Enter the Timer Time Format In HH:mm:ss :"));
                        Time time = Time.valueOf(format);
                        LocalTime localTime = time.toLocalTime();

                        int hr = localTime.getHour();
                        int min = localTime.getMinute();
                        int sc = localTime.getSecond();

                        long total = (hr * 60L * 60L) + (min * 60L) + sc;
                        timerTime(total);
                        TimerAlertEmail timerAlertEmail = new TimerAlertEmail();
                        timerAlertEmail.initializeAll();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Hmm, Something went Wrong Check the timer Format or Any Other Issue Please Try Again", "Alert", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex);
                    }
            }
        });

        jButton7.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(null,"Do You want to Schedule a Task ?","Information",JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                    choice = 1;
                    JOptionPane.showMessageDialog(null, "So Now Select The Row To Schedule");
                    table.addMouseListener(this);
                } else if (op == JOptionPane.CANCEL_OPTION) {
                    table.removeMouseListener(this);
                }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(choice == 0) {
            isCommited = false;
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int columnIndex = table.getColumnModel().getColumnIndex("Sr_no");
                Object idValue = table.getValueAt(selectedRow, columnIndex);
                try {
                    int op = JOptionPane.showConfirmDialog(null, "Want To Delete?", "Option", JOptionPane.YES_NO_OPTION);
                    if (op == JOptionPane.YES_OPTION) {
                        preparedStatement = connection.prepareStatement("DELETE FROM schedule_tb WHERE Sr_no = ?");
                        preparedStatement.setObject(1, idValue);
                        preparedStatement.executeUpdate();
                        showAll();
                        JOptionPane.showMessageDialog(null, "Deleted Successfully");
                    } else {
                        table.removeMouseListener(this);
                    }
                } catch (Exception r) {
                    r.printStackTrace();
                }

            }
        }
        if(choice == 1) {
            int op = JOptionPane.showConfirmDialog(null, "Do You want to Schedule a Task ?", "Information", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                jButton7.setEnabled(false);
                int selectedRow = table.getSelectedRow();
                int hour1 = LocalTime.now().getHour();
                int min1 = LocalTime.now().getMinute();
                int sec1 = LocalTime.now().getSecond();

                long sumOfLocalTime = (hour1 * 60L * 60L) + (min1 * 60L) + sec1;

                Time time1;
                if (selectedRow != -1) {
                    int columnIndex = table.getColumnModel().getColumnIndex("Sr_no");
                    Object idValue = table.getValueAt(selectedRow, columnIndex);
                    try {
                        preparedStatement = connection.prepareStatement("SELECT Schedule_Time FROM schedule_tb WHERE Sr_no = ?");
                        preparedStatement.setObject(1, idValue);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        time1 = resultSet.getTime("Schedule_Time");
                        LocalTime loc = time1.toLocalTime();
                        int hour = loc.getHour();
                        int min = loc.getMinute();
                        int sec = loc.getSecond();
                        long sumOfCurrent = (hour * 60L * 60L) + (min * 60L) + sec;
                        long diff = sumOfCurrent - sumOfLocalTime;
                        if (diff<0){
                            diff = (86400L - sumOfLocalTime) + sumOfCurrent;
                            JOptionPane.showMessageDialog(null,"Hmm, that's Tomorrow's task :) , ok na :)");
                            timerTime(diff);
                        }
                        else{
                        timerTime(diff);
                        }
                        preparedStatement = connection.prepareStatement("SELECT Schedule_Name FROM schedule_tb WHERE Sr_no = ?");
                        preparedStatement.setObject(1, idValue);
                        resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        String name = resultSet.getString("Schedule_Name");
                        EmailSender emailSender = new EmailSender();
                        emailSender.initializeAll(name);

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

}

package com.Projects.TaskSchedular.TaskScheduler;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.swing.*;
import java.util.Properties;

public class TimerAlertEmail {

    final String senderEmail = "sohamhajare81@gmail.com";
    final String appPassword = " your passcode";
    final String toEmail = "sohamhajare81@gmail.com";

    public void initializeAll() {
        // SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Enable debug to trace the session if needed
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

//        session.setDebug(true); // 🔍 Optional: shows detailed log in console

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("❌❌ Time is Over ❌❌");
            message.setText("Hi Soham, Your Timer is Over !! ✅✅");
            // Send the message
            Transport.send(message);
            System.out.println("✅ Email sent successfully!");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,"Something Went Wrong (Email is Not Sent) pls Check the Internet Connection","Warning",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
}

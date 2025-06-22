package com.Projects.TaskSchedular.TaskScheduler;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.swing.*;
import java.util.Properties;

public class EmailSender {

    final String senderEmail = "sohamhajare81@gmail.com";
    final String appPassword = "xlzaurnpnjlxvcjm"; // ⚠️ Put your App Password here
    final String toEmail = "sohamhajare81@gmail.com";

    public void initializeAll(String taskName) {
        // 🌐 SMTP Server Setup
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 🔐 Email Session with Auth
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        try {
            // ✉️ Create Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("⭕ Task Scheduler Timeout Alert ⭕");

            // 💌 HTML Email Body
            String htmlContent = """
<html>
<head>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f6f9fc;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 90%;
      max-width: 600px;
      margin: auto;
      background: white;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      overflow: hidden;
    }
    .header {
      background-color: #1f2937;
      padding: 20px;
      color: white;
      text-align: center;
    }
    .header img {
      width: 80px;
    }
    .content {
      padding: 20px;
    }
    .content h2 {
      color: #e11d48;
      margin-bottom: 10px;
    }
    .content p {
      color: #4b5563;
      font-size: 16px;
      line-height: 1.6;
    }
    .btn {
      display: inline-block;
      margin-top: 20px;
      padding: 12px 20px;
      background-color: #3b82f6;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      font-weight: bold;
    }
    .footer {
      text-align: center;
      padding: 20px;
      font-size: 12px;
      color: #9ca3af;
    }
    .icon {
      width: 50px;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="header">
      <img src="https://cdn-icons-png.flaticon.com/512/3570/3570270.png" alt="Task Logo">
      <h1>Task Scheduler Alert</h1>
    </div>
    <div class="content">
      <img class="icon" src="https://cdn-icons-png.flaticon.com/512/1828/1828665.png" alt="Warning Icon">
      <h2>⚠ Timeout Occurred</h2>
      <p>
        Your scheduled task <strong>
        """+taskName.toUpperCase()+"""
        </strong> could not complete within the allocated time limit.<br>
        <strong>Status:</strong> ❌ Timeout Failure
      </p>
      <p>
        <strong>Suggested Action:</strong> Please investigate the task's logic or server performance and try again.
      </p>
      <a href="https://google.com/" class="btn">📊 View Task Status</a>
    </div>
    <div class="footer">
      This is an automated message from your Java Task Scheduler.<br>
      Please do not reply to this email.
    </div>
  </div>
</body>
</html>
""";

            message.setContent(htmlContent, "text/html");
            Transport.send(message);
            System.out.println("✅ Email sent successfully!");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong (Email not sent).\nCheck internet or credentials.", "Warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
}

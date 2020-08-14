package com.saksham.calculator.logging.notification;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender implements NotificationsSender {
    @Override
    public void sendNotification(Notification notification) {
    	
    	 String password = "PUT_YOUR_PASSWORD";
         
         Properties properties = System.getProperties();
         properties.put("mail.smtp.host", "smtp.gmail.com");
         properties.put("mail.smtp.port", "587");
         properties.put("mail.smtp.ssl.enable", "true");
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable","true");
         Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(notification.getFromAddress(), password);
             }
         });

    	 try {    
    		 String msg = notification.getMessage() +"<br/><br/>"+ notification.getSignature();
             MimeMessage message = new MimeMessage(session);    
             message.setHeader("Content-Type", "text/html; charset=UTF-8");             
             message.setFrom(new InternetAddress(notification.getFromAddress()));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(notification.getToAddress()));
             message.setSubject(notification.getSubject());  
             Multipart multipart = new MimeMultipart();
             File attachment = notification.getAttachment();
             String filePath = attachment.getAbsolutePath();             
             DataSource reportsource = new FileDataSource(filePath);
             BodyPart messageBodyPart = new MimeBodyPart();
             messageBodyPart.setDataHandler(new DataHandler(reportsource));
             int index = filePath.indexOf("fileCreator");
             String fileName = filePath.substring(index+12);
             messageBodyPart.setFileName(fileName);
             multipart.addBodyPart(messageBodyPart);
             MimeBodyPart bodyHtml = new MimeBodyPart();
             bodyHtml.setContent(msg,"text/html");                       
             multipart.addBodyPart(bodyHtml);
             message.setContent(multipart);
             message.saveChanges();
             Transport.send(message); 
             System.out.println("Mail sent successfully");  
             attachment.delete();
           	} 
    	 	catch (MessagingException e) {
    	 		throw new RuntimeException(e);
        	}    
    }
}

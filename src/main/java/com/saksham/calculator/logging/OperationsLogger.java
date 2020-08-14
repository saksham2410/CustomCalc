package com.saksham.calculator.logging;

import com.saksham.calculator.FileFormat;
import com.saksham.calculator.LoggingStrategy;
import com.saksham.calculator.Operation;
import com.saksham.calculator.logging.fileCreator.LogfileCreator;
import com.saksham.calculator.logging.fileCreator.LogfileCreatorFactory;
import com.saksham.calculator.logging.notification.MailSender;
import com.saksham.calculator.logging.notification.Notification;
import com.saksham.calculator.logging.notification.NotificationsSender;

import java.io.File;
import java.util.List;

public class OperationsLogger {

    LogfileCreator logfileCreator;

    NotificationsSender notificationsSender;

    public OperationsLogger() {
    }

    private LogfileCreator getLogfileCreator(FileFormat fileFormat) {
        if (logfileCreator == null) {
            logfileCreator = LogfileCreatorFactory.create(fileFormat);
        }
        return logfileCreator;
    }

    private NotificationsSender getNotificationsSender() {
        if (notificationsSender == null) {
            notificationsSender = new MailSender();
        }
        return notificationsSender;
    }

    public void logOperations(List<Operation> operations, LoggingStrategy loggingStrategy) {
    	LogfileCreator logFile = getLogfileCreator(loggingStrategy.fileFormat);
    	File attachment = logFile.createLogFile(operations);
    	Notification notification = new Notification();	   
    	notification.setSubject("Logs");
    	notification.setFromAddress("saksham.g@zolostays.com");
    	notification.setSignature("Thanks");
    	notification.setMessage("test");
    	notification.setToAddress(loggingStrategy.email);
    	notification.setAttachment(attachment);  	
    	NotificationsSender notificationsSender = getNotificationsSender();
    	notificationsSender.sendNotification(notification);
    }
}

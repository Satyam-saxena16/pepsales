package com.example.pepsales.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;
import com.example.pepsales.repository.NotificationRepository;
import com.example.pepsales.service.NotificationService;

@Service
public class EmailNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);
    
    private final NotificationRepository notificationRepository;
    
    public EmailNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    @Override
    public boolean sendNotification(Notification notification) {
        if (!canHandle(notification)) {
            return false;
        }
        
        try {
            // In a real application, this would connect to an email service
            // like SendGrid, AWS SES, or JavaMail
            logger.info("Sending email notification to: {} with subject: {}", 
                    notification.getRecipient(), notification.getSubject());
            
            // Simulate sending email
            Thread.sleep(500);
            
            // Mark as sent and save
            notification.markAsSent();
            notificationRepository.save(notification);
            
            return true;
        } catch (Exception e) {
            logger.error("Failed to send email notification", e);
            return false;
        }
    }

    @Override
    public boolean canHandle(Notification notification) {
        return notification != null && NotificationType.EMAIL.equals(notification.getType());
    }
}
package com.example.pepsales.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;
import com.example.pepsales.repository.NotificationRepository;
import com.example.pepsales.service.NotificationService;

@Service
public class SmsNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationService.class);
    
    private final NotificationRepository notificationRepository;
    
    public SmsNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    @Override
    public boolean sendNotification(Notification notification) {
        if (!canHandle(notification)) {
            return false;
        }
        
        try {
            // In a real application, this would connect to an SMS service
            // like Twilio, Nexmo, or AWS SNS
            logger.info("Sending SMS notification to: {}", notification.getRecipient());
            
            // Simulate sending SMS
            Thread.sleep(300);
            
            // Mark as sent and save
            notification.markAsSent();
            notificationRepository.save(notification);
            
            return true;
        } catch (Exception e) {
            logger.error("Failed to send SMS notification", e);
            return false;
        }
    }

    @Override
    public boolean canHandle(Notification notification) {
        return notification != null && NotificationType.SMS.equals(notification.getType());
    }
}
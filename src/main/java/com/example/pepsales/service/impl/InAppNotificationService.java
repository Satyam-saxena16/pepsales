package com.example.pepsales.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;
import com.example.pepsales.repository.NotificationRepository;
import com.example.pepsales.service.NotificationService;

@Service
public class InAppNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(InAppNotificationService.class);
    
    private final NotificationRepository notificationRepository;
    
    public InAppNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    @Override
    public boolean sendNotification(Notification notification) {
        if (!canHandle(notification)) {
            return false;
        }
        
        try {
            // In a real application, this would connect to a websocket or push notification service
            // to deliver real-time notifications to the client application
            logger.info("Sending in-app notification to: {}", notification.getRecipient());
            
            // Simulate sending in-app notification
            Thread.sleep(100);
            
            // Mark as sent and save
            notification.markAsSent();
            notificationRepository.save(notification);
            
            return true;
        } catch (Exception e) {
            logger.error("Failed to send in-app notification", e);
            return false;
        }
    }

    @Override
    public boolean canHandle(Notification notification) {
        return notification != null && NotificationType.IN_APP.equals(notification.getType());
    }
}
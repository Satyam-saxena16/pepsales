package com.example.pepsales.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.repository.NotificationRepository;

@Service
public class NotificationManagerService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationManagerService.class);
    
    private final List<NotificationService> notificationServices;
    private final NotificationRepository notificationRepository;
    
    public NotificationManagerService(List<NotificationService> notificationServices, 
                                     NotificationRepository notificationRepository) {
        this.notificationServices = notificationServices;
        this.notificationRepository = notificationRepository;
    }
    
    /**
     * Sends a notification using the appropriate service
     * @param notification The notification to send
     * @return true if the notification was sent successfully, false otherwise
     */
    public boolean sendNotification(Notification notification) {
        if (notification == null) {
            logger.error("Cannot send null notification");
            return false;
        }
        
        // Save the notification first
        notification = notificationRepository.save(notification);
        
        // Find the appropriate service to handle this notification
        for (NotificationService service : notificationServices) {
            if (service.canHandle(notification)) {
                logger.info("Using service {} to send notification", service.getClass().getSimpleName());
                return service.sendNotification(notification);
            }
        }
        
        logger.error("No service found to handle notification of type: {}", notification.getType());
        return false;
    }
    
    /**
     * Gets all notifications
     * @return List of all notifications
     */
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    
    /**
     * Gets a notification by ID
     * @param id The notification ID
     * @return The notification, or null if not found
     */
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }
    
    /**
     * Gets all unsent notifications for a recipient
     * @param recipient The recipient
     * @return List of unsent notifications
     */
    public List<Notification> getUnsentNotificationsForRecipient(String recipient) {
        return notificationRepository.findByRecipientAndSent(recipient, false);
    }
}
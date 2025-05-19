package com.example.pepsales.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;
import com.example.pepsales.repository.NotificationRepository;

@Service
@EnableScheduling
public class ScheduledNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledNotificationService.class);
    
    private final NotificationManagerService notificationManagerService;
    private final NotificationRepository notificationRepository;
    
    public ScheduledNotificationService(NotificationManagerService notificationManagerService,
                                       NotificationRepository notificationRepository) {
        this.notificationManagerService = notificationManagerService;
        this.notificationRepository = notificationRepository;
    }
    
    /**
     * Process unsent email notifications every 5 minutes
     */
    @Scheduled(fixedRate = 300000) // 5 minutes
    public void processUnsentEmailNotifications() {
        logger.info("Processing unsent email notifications");
        List<Notification> unsentEmails = notificationRepository.findByTypeAndSent(NotificationType.EMAIL, false);
        
        for (Notification notification : unsentEmails) {
            logger.info("Processing unsent email notification: {}", notification.getId());
            notificationManagerService.sendNotification(notification);
        }
    }
    
    /**
     * Process unsent SMS notifications every 3 minutes
     */
    @Scheduled(fixedRate = 180000) // 3 minutes
    public void processUnsentSmsNotifications() {
        logger.info("Processing unsent SMS notifications");
        List<Notification> unsentSms = notificationRepository.findByTypeAndSent(NotificationType.SMS, false);
        
        for (Notification notification : unsentSms) {
            logger.info("Processing unsent SMS notification: {}", notification.getId());
            notificationManagerService.sendNotification(notification);
        }
    }
    
    /**
     * Process unsent in-app notifications every minute
     */
    @Scheduled(fixedRate = 60000) // 1 minute
    public void processUnsentInAppNotifications() {
        logger.info("Processing unsent in-app notifications");
        List<Notification> unsentInApp = notificationRepository.findByTypeAndSent(NotificationType.IN_APP, false);
        
        for (Notification notification : unsentInApp) {
            logger.info("Processing unsent in-app notification: {}", notification.getId());
            notificationManagerService.sendNotification(notification);
        }
    }
}
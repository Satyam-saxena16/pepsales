package com.example.pepsales.service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;

public class SmsNotificationService implements NotificationService {
    @Override
    public boolean canHandle(Notification notification) {
        return notification.getType() == NotificationType.SMS;
    }

    @Override
    public boolean sendNotification(Notification notification) {
        // Simulate SMS sending
        System.out.println("Sending SMS to: " + notification.getRecipient());
        System.out.println("Content: " + notification.getContent());
        return true;
    }
}
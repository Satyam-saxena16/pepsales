package com.example.pepsales.service;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;

public class EmailNotificationService implements NotificationService {
    @Override
    public boolean canHandle(Notification notification) {
        return notification.getType() == NotificationType.EMAIL;
    }

    @Override
    public boolean sendNotification(Notification notification) {
        // Simulate email sending
        System.out.println("Sending email to: " + notification.getRecipient());
        System.out.println("Subject: " + notification.getSubject());
        System.out.println("Content: " + notification.getContent());
        return true;
    }
}
package com.example.pepsales.service;

import com.example.pepsales.model.Notification;

public interface NotificationService {
    boolean canHandle(Notification notification);
    boolean sendNotification(Notification notification);
}
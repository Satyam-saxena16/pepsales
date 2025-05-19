package com.example.pepsales.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;

class SmsNotificationServiceTest {

    private SmsNotificationService smsService = new SmsNotificationService();
    
    @Test
    void canHandle_WithSmsNotification_ReturnsTrue() {
        Notification notification = new Notification();
        notification.setType(NotificationType.SMS);
        
        assertTrue(smsService.canHandle(notification));
    }
    
    @Test
    void canHandle_WithNonSmsNotification_ReturnsFalse() {
        Notification notification = new Notification();
        notification.setType(NotificationType.EMAIL);
        
        assertFalse(smsService.canHandle(notification));
    }
    
    @Test
    void sendNotification_WithValidSms_ReturnsTrue() {
        Notification notification = new Notification();
        notification.setType(NotificationType.SMS);
        notification.setRecipient("+1234567890");
        notification.setContent("Test message");
        
        assertTrue(smsService.sendNotification(notification));
    }
}
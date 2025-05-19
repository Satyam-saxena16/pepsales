package com.example.pepsales.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;

class EmailNotificationServiceTest {

    private EmailNotificationService emailService = new EmailNotificationService();
    
    @Test
    void canHandle_WithEmailNotification_ReturnsTrue() {
        Notification notification = new Notification();
        notification.setType(NotificationType.EMAIL);
        
        assertTrue(emailService.canHandle(notification));
    }
    
    @Test
    void canHandle_WithNonEmailNotification_ReturnsFalse() {
        Notification notification = new Notification();
        notification.setType(NotificationType.SMS);
        
        assertFalse(emailService.canHandle(notification));
    }
    
    @Test
    void sendNotification_WithValidEmail_ReturnsTrue() {
        Notification notification = new Notification();
        notification.setType(NotificationType.EMAIL);
        notification.setRecipient("test@example.com");
        notification.setSubject("Test");
        notification.setContent("Test content");
        
        assertTrue(emailService.sendNotification(notification));
    }
}
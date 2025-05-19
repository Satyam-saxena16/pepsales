package com.example.pepsales.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;
import com.example.pepsales.repository.NotificationRepository;

@ExtendWith(MockitoExtension.class)
class NotificationManagerServiceTest {

    @Mock
    private NotificationRepository notificationRepository;
    
    @Mock
    private EmailNotificationService emailService;
    
    @Mock
    private SmsNotificationService smsService;
    
    @InjectMocks
    private NotificationManagerService notificationManagerService;
    
    private Notification testNotification;
    
    @BeforeEach
    void setUp() {
        notificationManagerService = new NotificationManagerService(
            Arrays.asList(emailService, smsService),
            notificationRepository
        );
        
        testNotification = new Notification();
        testNotification.setType(NotificationType.EMAIL);
        testNotification.setRecipient("test@example.com");
        testNotification.setSubject("Test");
        testNotification.setContent("Test content");
    }
    
    @Test
    void sendNotification_WithValidEmailNotification_ReturnsTrue() {
        when(emailService.canHandle(testNotification)).thenReturn(true);
        when(emailService.sendNotification(testNotification)).thenReturn(true);
        when(notificationRepository.save(testNotification)).thenReturn(testNotification);
        
        assertTrue(notificationManagerService.sendNotification(testNotification));
    }
    
    @Test
    void sendNotification_WithNoMatchingService_ReturnsFalse() {
        testNotification.setType(NotificationType.IN_APP);
        
        lenient().when(emailService.canHandle(testNotification)).thenReturn(false);
        lenient().when(smsService.canHandle(testNotification)).thenReturn(false);
        
        assertFalse(notificationManagerService.sendNotification(testNotification));
    }
}
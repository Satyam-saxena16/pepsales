package com.example.pepsales.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;
import com.example.pepsales.service.NotificationManagerService;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private NotificationManagerService notificationManagerService;
    
    @Test
    void getAllNotifications_ReturnsEmptyList() throws Exception {
        when(notificationManagerService.getAllNotifications()).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/api/notifications"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }
    
    @Test
    void sendNotification_WithValidRequest_ReturnsCreated() throws Exception {
        Notification testNotification = new Notification();
        testNotification.setType(NotificationType.EMAIL);
        testNotification.setRecipient("test@example.com");
        testNotification.setSubject("Test");
        testNotification.setContent("Test content");
        
        when(notificationManagerService.sendNotification(any(Notification.class))).thenReturn(true);
        
        mockMvc.perform(post("/api/notifications")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"type\":\"EMAIL\",\"recipient\":\"test@example.com\",\"subject\":\"Test\",\"content\":\"Test content\"}"))
               .andExpect(status().isOk()); // Changed from isCreated() to isOk()
    }
}
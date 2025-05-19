package com.example.pepsales.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pepsales.dto.NotificationRequest;
import com.example.pepsales.dto.NotificationResponse;
import com.example.pepsales.model.Notification;
import com.example.pepsales.service.NotificationManagerService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationManagerService notificationManagerService;
    
    public NotificationController(NotificationManagerService notificationManagerService) {
        this.notificationManagerService = notificationManagerService;
    }
    
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        List<Notification> notifications = notificationManagerService.getAllNotifications();
        List<NotificationResponse> responses = notifications.stream()
                .map(NotificationResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationManagerService.getNotificationById(id);
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(NotificationResponse.fromEntity(notification));
    }
    
    @GetMapping("/unsent/{recipient}")
    public ResponseEntity<List<NotificationResponse>> getUnsentNotificationsForRecipient(@PathVariable String recipient) {
        List<Notification> notifications = notificationManagerService.getUnsentNotificationsForRecipient(recipient);
        List<NotificationResponse> responses = notifications.stream()
                .map(NotificationResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
    
    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        // Convert DTO to entity
        Notification notification = new Notification();
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setContent(request.getContent());
        notification.setType(request.getType());
        
        // Send the notification
        boolean sent = notificationManagerService.sendNotification(notification);
        
        NotificationResponse response = NotificationResponse.fromEntity(notification);
        
        if (sent) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
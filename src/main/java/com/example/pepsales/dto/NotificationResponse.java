package com.example.pepsales.dto;

import java.time.LocalDateTime;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;

/**
 * Data Transfer Object for notification responses
 */
public class NotificationResponse {
    
    private Long id;
    private String recipient;
    private String subject;
    private String content;
    private NotificationType type;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
    private boolean sent;
    
    public NotificationResponse() {
    }
    
    /**
     * Create a response DTO from a notification entity
     * @param notification The notification entity
     * @return A new NotificationResponse
     */
    public static NotificationResponse fromEntity(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setRecipient(notification.getRecipient());
        response.setSubject(notification.getSubject());
        response.setContent(notification.getContent());
        response.setType(notification.getType());
        response.setCreatedAt(notification.getCreatedAt());
        response.setSentAt(notification.getSentAt());
        response.setSent(notification.isSent());
        return response;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public NotificationType getType() {
        return type;
    }
    
    public void setType(NotificationType type) {
        this.type = type;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
    
    public boolean isSent() {
        return sent;
    }
    
    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
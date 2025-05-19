package com.example.pepsales.dto;

import com.example.pepsales.model.NotificationType;

/**
 * Data Transfer Object for notification requests
 */
public class NotificationRequest {
    
    private String recipient;
    private String subject;
    private String content;
    private NotificationType type;
    
    public NotificationRequest() {
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
}
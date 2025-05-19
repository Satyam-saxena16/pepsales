package com.example.pepsales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pepsales.model.Notification;
import com.example.pepsales.model.NotificationType;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    List<Notification> findByType(NotificationType type);
    
    List<Notification> findByRecipientAndSent(String recipient, boolean sent);
    
    List<Notification> findByTypeAndSent(NotificationType type, boolean sent);
}
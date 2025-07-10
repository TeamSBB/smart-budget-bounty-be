package com.smartbudgetbounty.dto.notification;

import com.smartbudgetbounty.enums.NotificationType;

import java.time.Instant;

public class NotificationDataDto {
    private Long notificationId;
    private String message;
    private boolean read;
    private Instant showNotificationDate;
    private NotificationType type;

    public NotificationDataDto(Long notificationId, String message, boolean read, Instant showNotificationDate, NotificationType type) {
        this.notificationId = notificationId;
        this.message = message;
        this.read = read;
        this.showNotificationDate = showNotificationDate;
        this.type = type;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Instant getShowNotificationDate() {
        return showNotificationDate;
    }

    public void setShowNotificationDate(Instant showNotificationDate) {
        this.showNotificationDate = showNotificationDate;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}

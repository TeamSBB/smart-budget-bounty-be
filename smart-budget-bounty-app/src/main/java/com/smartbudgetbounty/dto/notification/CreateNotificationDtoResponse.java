package com.smartbudgetbounty.dto.notification;

import com.smartbudgetbounty.enums.NotificationType;

import java.time.Instant;

public class CreateNotificationDtoResponse {
    private Long id;
    private String message;
    private Instant showNotificationDate;
    private NotificationType type;

    public CreateNotificationDtoResponse(Long id, String message, Instant showNotificationDate, NotificationType type) {
        this.id = id;
        this.message = message;
        this.showNotificationDate = showNotificationDate;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

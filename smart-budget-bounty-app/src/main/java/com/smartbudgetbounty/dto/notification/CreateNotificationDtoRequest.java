package com.smartbudgetbounty.dto.notification;

import com.smartbudgetbounty.enums.NotificationType;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class CreateNotificationDtoRequest {
    @NotNull(message = "User ID is required.")
    private Long userId;

    @NotNull(message = "Message is required.")
    private String message;

    @NotNull(message = "Show Notification Date is required.")
    private Instant showNotificationDate;

    @NotNull(message = "Notification Type is required.")
    private NotificationType type;

    public CreateNotificationDtoRequest(Long userId, String message, Instant showNotificationDate, NotificationType type) {
        this.userId = userId;
        this.message = message;
        this.showNotificationDate = showNotificationDate;
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

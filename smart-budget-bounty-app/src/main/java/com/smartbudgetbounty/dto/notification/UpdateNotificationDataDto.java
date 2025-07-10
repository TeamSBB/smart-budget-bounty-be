package com.smartbudgetbounty.dto.notification;

import jakarta.validation.constraints.NotNull;

public class UpdateNotificationDataDto {
    @NotNull(message = "Notification Id is required.")
    private Long notificationId;

    @NotNull(message = "Read is required.")
    private boolean read;

    public UpdateNotificationDataDto(Long notificationId, boolean read) {
        this.notificationId = notificationId;
        this.read = read;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}

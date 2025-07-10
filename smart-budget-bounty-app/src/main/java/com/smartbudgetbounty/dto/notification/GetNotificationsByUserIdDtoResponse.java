package com.smartbudgetbounty.dto.notification;

import java.util.List;

public class GetNotificationsByUserIdDtoResponse {
    private List<NotificationDataDto> data;

    public GetNotificationsByUserIdDtoResponse(List<NotificationDataDto> data) {
        this.data = data;
    }

    public List<NotificationDataDto> getData() {
        return data;
    }

    public void setData(List<NotificationDataDto> data) {
        this.data = data;
    }
}

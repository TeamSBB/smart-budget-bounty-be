package com.smartbudgetbounty.service.notification;

import com.smartbudgetbounty.dto.notification.*;

public interface NotificationService {
    CreateNotificationDtoResponse createNotification(CreateNotificationDtoRequest requestDto);

    UpdateListOfNotificationsByIdDtoResponse updateListOfNotificationsById(UpdateListOfNotificationsByIdDtoRequest requestDto);

    GetNotificationsByUserIdDtoResponse getNotificationsByUserId(Long userId);
}

package com.smartbudgetbounty.service.notification;

import com.smartbudgetbounty.dto.notification.*;
import com.smartbudgetbounty.entity.Notification;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.NotificationRepository;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.DateTimeUtil;
import com.smartbudgetbounty.util.LogUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final UserService userService;
    private final NotificationRepository notificationRepo;

    public NotificationServiceImpl(UserService userService, NotificationRepository notificationRepo) {
        super();
        this.userService = userService;
        this.notificationRepo = notificationRepo;
    }

    @Override
    public CreateNotificationDtoResponse createNotification(CreateNotificationDtoRequest requestDto) {
        LogUtil.logStart(logger, "Start createNotification.");

        User searchUser = userService.getById(requestDto.getUserId());

        Notification newNotification = notificationRepo.save(new Notification(
                        requestDto.getMessage(),
                        false,
                        requestDto.getShowNotificationDate() != null ? DateTimeUtil.stripTimeToStartOfDayUTC(requestDto.getShowNotificationDate())
                                : DateTimeUtil.stripTimeToStartOfDayUTC(Instant.now()),
                        requestDto.getType(),
                        searchUser
                )
        );

        LogUtil.logEnd(logger, "End createNotification.");

        return new CreateNotificationDtoResponse(
                newNotification.getId(),
                newNotification.getMessage(),
                newNotification.getShowNotificationDate(),
                newNotification.getType()
        );
    }

    @Transactional
    @Override
    public UpdateListOfNotificationsByIdDtoResponse updateListOfNotificationsById(UpdateListOfNotificationsByIdDtoRequest requestDto) {
        LogUtil.logStart(logger, "Start updateListOfNotificationsById.");

        List<Notification> notificationsToUpdate = new ArrayList<>();

        for (UpdateNotificationDataDto data : requestDto.getData()) {
            Notification notification = notificationRepo.findById(data.getNotificationId()).orElseThrow(() -> {
                LogUtil.logError(logger, "Unable to find notificationId: {}.", data.getNotificationId());
                return new EntityNotFoundException("Unable to find notificationId: " + data.getNotificationId());
            });

            notification.setRead(data.isRead());
            notificationsToUpdate.add(notification);
        }
        notificationRepo.saveAll(notificationsToUpdate);

        LogUtil.logEnd(logger, "End updateListOfNotificationsById.");
        return new UpdateListOfNotificationsByIdDtoResponse(requestDto.getData());
    }


    @Override
    public GetNotificationsByUserIdDtoResponse getNotificationsByUserId(Long userId) {
        LogUtil.logStart(logger, "Start getNotificationsByUserId.");

        List<Notification> notifications = notificationRepo.findAllByUserId(userId);

        if (notifications.isEmpty()) {
            LogUtil.logEnd(logger, "No notifications found for userId: {}.", userId);
            return new GetNotificationsByUserIdDtoResponse(null);
        }

        List<NotificationDataDto> notificationsDataDto = notifications.stream()
                .filter(notification -> !notification.isRead() &&
                        DateTimeUtil.isDateTimeReached(notification.getShowNotificationDate()))
                .map(notification -> new NotificationDataDto(
                        notification.getId(),
                        notification.getMessage(),
                        notification.isRead(),
                        notification.getShowNotificationDate(),
                        notification.getType()))
                .toList();


        LogUtil.logEnd(logger, "End getNotificationsByUserId.");

        return new GetNotificationsByUserIdDtoResponse(notificationsDataDto);
    }
}

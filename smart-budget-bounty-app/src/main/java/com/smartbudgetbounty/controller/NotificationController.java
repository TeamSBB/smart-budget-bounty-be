package com.smartbudgetbounty.controller;

import com.smartbudgetbounty.dto.notification.*;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.notification.NotificationService;
import com.smartbudgetbounty.util.LogUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Notification",
        description = "Endpoints for the Notification entity"
)
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;


    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getNotificationsByUserId(@PathVariable Long userId) {
        LogUtil.logInfoController(logger, "API called: GET /api/notification/" + userId);

        GetNotificationsByUserIdDtoResponse getResponseDto = notificationService.getNotificationsByUserId(userId);

        return ResponseEntity.ok(new ApiResponseBody<>(
                getResponseDto,
                "getNotificationsByUserId successfully."
        ));
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@Valid @RequestBody CreateNotificationDtoRequest requestDto) {
        LogUtil.logInfoController(logger, "API called: POST /api/notification");

        // Call service to insert into db
        CreateNotificationDtoResponse responseDto = notificationService.createNotification(requestDto);

        return ResponseEntity.ok(new ApiResponseBody<>(
                responseDto,
                "createNotification successfully."
        ));
    }

    @PutMapping
    public ResponseEntity<?> updateListOfNotificationsById(@Valid @RequestBody UpdateListOfNotificationsByIdDtoRequest requestDto) {
        LogUtil.logInfoController(logger, "API called: PUT /api/notification/");

        UpdateListOfNotificationsByIdDtoResponse responseDto = notificationService.updateListOfNotificationsById(requestDto);

        return ResponseEntity.ok(new ApiResponseBody<>(
                responseDto,
                "updateListOfNotificationsById successfully."
        ));
    }

}

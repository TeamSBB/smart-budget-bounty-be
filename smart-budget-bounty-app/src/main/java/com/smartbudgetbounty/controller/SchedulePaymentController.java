package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoResponse;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.schedulepayment.SchedulePaymentService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/schedulePayment")
public class SchedulePaymentController {
    private static final Logger logger = LoggerFactory.getLogger(SchedulePaymentController.class);

    private final SchedulePaymentService schedulePaymentService;

    public SchedulePaymentController(SchedulePaymentService schedulePaymentService) {
        this.schedulePaymentService = schedulePaymentService;
    }
    
    @PostMapping()
    public ResponseEntity<?> createSchedulePayment(@Valid @RequestBody CreateSchedulePaymentDtoRequest createDtoReq) {
        LogUtil.logInfoController(logger, "API called: POST /api/schedulePayment");
        
        // Call service to insert into db
        CreateSchedulePaymentDtoResponse createResponseDto = schedulePaymentService.create(createDtoReq);
        
        return ResponseEntity.ok(new ApiResponse<>(
    		createResponseDto,
            "Scheduled successfully."
        ));    
    }
}

package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentDtoResponse;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.schedulepayment.SchedulePaymentService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/schedulePayment")
public class SchedulePaymentController {
    private static final Logger logger = LoggerFactory.getLogger(SchedulePaymentController.class);
    
    @Autowired
    private SchedulePaymentService schedulePaymentService;
    
    //Post: Create a new scheduled payment
    @PostMapping
    public ResponseEntity<?> createSchedule(@Valid @RequestBody SchedulePaymentDtoRequest requestDto) {
    	LogUtil.logInfoController(logger, "API called: POST /api/schedulePayment");
    	SchedulePaymentDtoResponse response = schedulePaymentService.createSchedule(requestDto);
    	return ResponseEntity.ok().body(new ApiResponseBody<>(
    			response, "Scheduled successfully."
    			));
    }
    
//    @PostMapping()
//    public ResponseEntity<?> createSchedulePayment(@Valid @RequestBody SchedulePaymentDtoRequest createDtoReq) {
//        LogUtil.logInfoController(logger, "API called: POST /api/schedulePayment");
//        
//        // Call service to insert into db
//        SchedulePaymentDtoResponse createResponseDto = schedulePaymentService.create(createDtoReq);
//        
//        return ResponseEntity.ok(new ApiResponse<>(
//    		createResponseDto,
//            "Scheduled successfully."
//        ));    
//    }
    
    //Get: Retrieve all Schedules set by the user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllSchedulesByUser(
    		@PathVariable Long userId) {
    	List<SchedulePaymentDtoResponse> schedules = 
    			schedulePaymentService.getAllSchedulesByUser(userId);
    	return ResponseEntity.ok().body(
    			new ApiResponseBody<>(
    					schedules, "Fetched all schedules successfully."
    			));
    }
}
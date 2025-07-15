package com.smartbudgetbounty.controller;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentCreateRequestDto;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentResponseDto;
import com.smartbudgetbounty.dto.schedulepayment.SearchSchedulePaymentResponseDto;
import com.smartbudgetbounty.service.schedulepayment.SchedulePaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule-payments")
public class SchedulePaymentController {


    @Autowired
    private SchedulePaymentService service;

    @PostMapping("{userId}")
    public SchedulePaymentResponseDto create(@Valid @PathVariable Long userId, @RequestBody SchedulePaymentCreateRequestDto request) {
        return service.createSchedulePayment(userId, request);
    }

    @GetMapping
    public List<SchedulePaymentResponseDto> getAll() {
        return service.getAllSchedulePayments();
    }


    @GetMapping("/status/{status}")
    public List<SchedulePaymentResponseDto> getByStatus(@PathVariable String status) {
        return service.getSchedulePaymentsByStatus(status);
    }


    @GetMapping("/search")
    public SearchSchedulePaymentResponseDto search(
            @RequestParam(required = false) String paymentMethod,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Instant fromDate,
            @RequestParam(required = false) Instant toDate,
            @RequestParam(required = false) Instant fromDate,
            @RequestParam(required = false) Instant toDate,
            Pageable pageable) {


        return service.searchSchedulePayments(paymentMethod, status, fromDate, toDate, pageable);
    }

    @PostMapping("/dailyScheduler")
    public ResponseEntity<Map<String, Object>> dailyScheduleCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success.");

        int totalTransferCreated = service.dailyScheduleCheck();
        response.put("TransferCreated", totalTransferCreated);

        return ResponseEntity.ok(response);
    }

}
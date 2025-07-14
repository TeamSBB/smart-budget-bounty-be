package com.smartbudgetbounty.controller;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentCreateRequestDto;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentResponseDto;
import com.smartbudgetbounty.service.schedulepayment.SchedulePaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/schedule-payments")
public class SchedulePaymentController {

    @Autowired
    private SchedulePaymentService service;

    @PostMapping
    public SchedulePaymentResponseDto create(@Valid @RequestBody SchedulePaymentCreateRequestDto request) {
        return service.createSchedulePayment(request);
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
    public Page<SchedulePaymentResponseDto> search(
            @RequestParam(required = false) String paymentMethod,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Instant fromDate,
            @RequestParam(required = false) Instant toDate,
            Pageable pageable) {

        return service.searchSchedulePayments(paymentMethod, status, fromDate, toDate, pageable);
    }
}
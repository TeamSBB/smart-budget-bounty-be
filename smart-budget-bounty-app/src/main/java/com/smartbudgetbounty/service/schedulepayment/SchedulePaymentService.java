package com.smartbudgetbounty.service.schedulepayment;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentCreateRequestDto;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentResponseDto;
import com.smartbudgetbounty.dto.schedulepayment.SearchSchedulePaymentResponseDto;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface SchedulePaymentService {

    SchedulePaymentResponseDto createSchedulePayment(Long userId, SchedulePaymentCreateRequestDto request);

    List<SchedulePaymentResponseDto> getAllSchedulePayments();

    List<SchedulePaymentResponseDto> getSchedulePaymentsByStatus(String status);

    SearchSchedulePaymentResponseDto searchSchedulePayments(String paymentMethod, String status, Instant fromDate, Instant toDate, Pageable pageable);

    int dailyScheduleCheck();
}
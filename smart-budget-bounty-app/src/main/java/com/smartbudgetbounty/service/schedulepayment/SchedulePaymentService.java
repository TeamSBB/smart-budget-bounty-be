package com.smartbudgetbounty.service.schedulepayment;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentCreateRequestDto;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
 
public interface SchedulePaymentService {
 
    SchedulePaymentResponseDto createSchedulePayment(SchedulePaymentCreateRequestDto request);
 
    List<SchedulePaymentResponseDto> getAllSchedulePayments();
 
    List<SchedulePaymentResponseDto> getSchedulePaymentsByStatus(String status);
 
    Page<SchedulePaymentResponseDto> searchSchedulePayments(String paymentMethod, String status, Instant fromDate, Instant toDate, Pageable pageable);
}
package com.smartbudgetbounty.service.schedulepayment;

import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoResponse;

@Service
public interface SchedulePaymentService {
	CreateSchedulePaymentDtoResponse create(CreateSchedulePaymentDtoRequest request);
}

package com.smartbudgetbounty.service.schedulepayment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentDtoResponse;

public interface SchedulePaymentService {
	SchedulePaymentDtoResponse createSchedule(SchedulePaymentDtoRequest dto);
	List<SchedulePaymentDtoResponse> getAllSchedulesByUser(Long userId);
}

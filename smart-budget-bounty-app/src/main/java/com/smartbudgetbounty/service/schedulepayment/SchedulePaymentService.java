package com.smartbudgetbounty.service.schedulepayment;

import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoResponse;

public interface SchedulePaymentService {
	CreateSchedulePaymentDtoResponse create(CreateSchedulePaymentDtoRequest request);
}

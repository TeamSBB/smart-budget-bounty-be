package com.smartbudgetbounty.service.schedulepayment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.CreateSchedulePaymentDtoResponse;
import com.smartbudgetbounty.entity.SchedulePayment;
import com.smartbudgetbounty.repository.SchedulePaymentRepository;
import com.smartbudgetbounty.util.LogUtil;

@Service
public class SchedulePaymentServiceImpl implements SchedulePaymentService {

	private static final Logger logger = LoggerFactory
			.getLogger(SchedulePaymentServiceImpl.class);

	private final SchedulePaymentRepository repo;

	public SchedulePaymentServiceImpl(SchedulePaymentRepository repo) {
		this.repo = repo;
	}
	
	private CreateSchedulePaymentDtoResponse toDtoResponse(SchedulePayment entity) {
		CreateSchedulePaymentDtoResponse response = new CreateSchedulePaymentDtoResponse(
				entity.getBillName());
	
		return response;
	}
	
	@Override
	public CreateSchedulePaymentDtoResponse create(
			CreateSchedulePaymentDtoRequest request) {
        LogUtil.logStart(logger, "Creating new SchedulePayment with data.");
        
        SchedulePayment entity = new SchedulePayment(
        		request.getBillName(), 
        		request.getBillAmount(), 
        		request.getStartDate(), 
        		request.isRecurringFlag(), 
        		null);     
        repo.save(entity);
        
        CreateSchedulePaymentDtoResponse response = toDtoResponse(entity);
        
        LogUtil.logEnd(logger, "Created SchedulePayment: {}", response);
        
        return response;
	}

}

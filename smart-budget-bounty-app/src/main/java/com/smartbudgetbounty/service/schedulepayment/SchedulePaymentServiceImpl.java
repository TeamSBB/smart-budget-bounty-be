package com.smartbudgetbounty.service.schedulepayment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentCreateRequestDto;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentResponseDto;
import com.smartbudgetbounty.entity.SchedulePayment;
import com.smartbudgetbounty.repository.SchedulePaymentRepository;
import com.smartbudgetbounty.service.schedulepayment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class SchedulePaymentServiceImpl implements SchedulePaymentService {
 
    @Autowired
    private SchedulePaymentRepository repository;
 
    @Override
    public SchedulePaymentResponseDto createSchedulePayment(SchedulePaymentCreateRequestDto request) {
        SchedulePayment entity = new SchedulePayment();
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setAccountId(request.getAccountId());
        entity.setBankName(request.getBankName());
        entity.setBankCode(request.getBankCode());
        entity.setBranchCode(request.getBranchCode());
        entity.setRecipientAccountNumber(request.getRecipientAccountNumber());
        entity.setAccountHolderName(request.getAccountHolderName());
        entity.setBillingOrganizationCode(request.getBillingOrganizationCode());
        entity.setGiroAmount(request.getGiroAmount());
        entity.setGiroFrequency(request.getGiroFrequency());
        entity.setGiroStartDate(request.getGiroStartDate());
        entity.setRecipientName(request.getRecipientName());
        entity.setStandingRecipientAccountNumber(request.getStandingRecipientAccountNumber());
        entity.setStandingAmount(request.getStandingAmount());
        entity.setFrequency(request.getFrequency());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus("Processing");
 
        SchedulePayment saved = repository.save(entity);
        return mapToResponse(saved);
    }
 
    @Override
    public List<SchedulePaymentResponseDto> getAllSchedulePayments() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<SchedulePaymentResponseDto> getSchedulePaymentsByStatus(String status) {
        return repository.findByStatusIgnoreCase(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
 
//    @Override
//    public Page<SchedulePaymentResponseDto> searchSchedulePayments(
//            String paymentMethod, String status, Instant fromDate, Instant toDate, Pageable pageable) {
// 
//        Page<SchedulePayment> page;
// 
//        if (status != null && paymentMethod != null) {
//            page = repository.findByStatusIgnoreCaseAndPaymentMethodIgnoreCase(status, paymentMethod, pageable);
//        } else if (status != null) {
//            page = repository.findByStatusIgnoreCase(status, pageable);
//        } else if (paymentMethod != null) {
//            page = repository.findByPaymentMethodIgnoreCase(paymentMethod, pageable);
//        } else {
//            page = repository.findAll(pageable);
//        }
// 
//        List<SchedulePayment> filtered = page.stream()
//                .filter(p -> (fromDate == null || !p.getStartDate().isBefore(fromDate)) &&
//                             (toDate == null || !p.getStartDate().isAfter(toDate)))
//                .collect(Collectors.toList());
// 
//        List<SchedulePaymentResponseDto> dtoList = filtered.stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
// 
//        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
//    }
 
    private SchedulePaymentResponseDto mapToResponse(SchedulePayment entity) {
        SchedulePaymentResponseDto dto = new SchedulePaymentResponseDto();
        dto.setId(entity.getId());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setStatus(entity.getStatus());
 
        if ("Giro".equalsIgnoreCase(entity.getPaymentMethod())) {
            dto.setRecipient(entity.getAccountHolderName());
            dto.setAmount(entity.getGiroAmount());
            dto.setFrequency(entity.getGiroFrequency());
            dto.setStartDate(entity.getGiroStartDate());
        } else if ("Standing Instruction".equalsIgnoreCase(entity.getPaymentMethod())) {
            dto.setRecipient(entity.getRecipientName());
            dto.setAmount(entity.getStandingAmount());
            dto.setFrequency(entity.getFrequency());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
        }
        return dto;
    }

	@Override
	public Page<SchedulePaymentResponseDto> searchSchedulePayments(String paymentMethod, String status,
			Instant fromDate, Instant toDate, Pageable pageable) {
			 
	        Page<SchedulePayment> page;
	 
	        if (status != null && paymentMethod != null) {
	            page = repository.findByStatusIgnoreCaseAndPaymentMethodIgnoreCase(status, paymentMethod, pageable);
	        } else if (status != null) {
	            page = repository.findByStatusIgnoreCase(status, pageable);
	        } else if (paymentMethod != null) {
	            page = repository.findByPaymentMethodIgnoreCase(paymentMethod, pageable);
	        } else {
	            page = repository.findAll(pageable);
	        }
	 
	        List<SchedulePayment> filtered = page.stream()
	                .filter(p -> (fromDate == null || !p.getStartDate().isBefore(fromDate)) &&
	                             (toDate == null || !p.getStartDate().isAfter(toDate)))
	                .collect(Collectors.toList());
	 
	        List<SchedulePaymentResponseDto> dtoList = filtered.stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	 
	        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
		// TODO Auto-generated method stub
	}
}
package com.smartbudgetbounty.service.schedulepayment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentDtoRequest;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentDtoResponse;
import com.smartbudgetbounty.entity.SchedulePayment;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.SchedulePaymentRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.service.schedulepayment.SchedulePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class SchedulePaymentServiceImpl implements SchedulePaymentService {
 
    @Autowired
    private SchedulePaymentRepository scheduleRepo;
 
    @Autowired
    private UserRepository userRepo;
 
    @Override
    public SchedulePaymentDtoResponse createSchedule(SchedulePaymentDtoRequest dto) {
        User user = userRepo.findById(dto.getUserId())
                            .orElseThrow(() -> new RuntimeException("User not found"));
 
        SchedulePayment entity = new SchedulePayment(
        		dto.getUserId(),
                dto.getRecipientName(),
                dto.getPaymentMethod(),
                dto.getBankName(),
                dto.getAccountNumber(),
                dto.getTransferLimit(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getFrequency(),
                dto.getCardNumber(),
                dto.getNameOnCard(),
                dto.getExpiryDate(),
                dto.getCvv(),
                user
        );
 
SchedulePayment saved = scheduleRepo.save(entity);
 
        return mapToResponseDto(saved);
    }
 
    @Override
    public List<SchedulePaymentDtoResponse> getAllSchedulesByUser(Long userId) {
        return scheduleRepo.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
 
    private SchedulePaymentDtoResponse mapToResponseDto(SchedulePayment entity) {
        SchedulePaymentDtoResponse dto = new SchedulePaymentDtoResponse();
        dto.setId(entity.getId());
        dto.setRecipientName(entity.getRecipientName());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setBankName(entity.getBankName());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setTransferLimit(entity.getTransferLimit());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setFrequency(entity.getFrequency());
        dto.setCardNumber(entity.getCardNumber());
        dto.setNameOnCard(entity.getNameOnCard());
        dto.setExpiryDate(entity.getExpiryDate());
        dto.setCvv(entity.getCvv());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }
}
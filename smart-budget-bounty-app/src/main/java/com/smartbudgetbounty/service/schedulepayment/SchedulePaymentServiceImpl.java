package com.smartbudgetbounty.service.schedulepayment;

import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentCreateRequestDto;
import com.smartbudgetbounty.dto.schedulepayment.SchedulePaymentResponseDto;
import com.smartbudgetbounty.dto.schedulepayment.SearchSchedulePaymentResponseDto;
import com.smartbudgetbounty.dto.transfer.CreateTransferRequestDto;
import com.smartbudgetbounty.entity.SchedulePayment;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.SchedulePaymentRepository;
import com.smartbudgetbounty.service.paymentmethod.PaymentMethodService;
import com.smartbudgetbounty.service.transfer.TransferService;
import com.smartbudgetbounty.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulePaymentServiceImpl implements SchedulePaymentService {

    @Autowired
    private SchedulePaymentRepository repository;

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Override
    public SchedulePaymentResponseDto createSchedulePayment(Long userId, SchedulePaymentCreateRequestDto request) {
        SchedulePayment entity = new SchedulePayment();
        User user = userService.getById(userId);
        entity.setUser(user);
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

    private SchedulePaymentResponseDto mapToResponse(SchedulePayment entity) {
        SchedulePaymentResponseDto dto = new SchedulePaymentResponseDto();
        dto.setId(entity.getId());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setStatus(entity.getStatus());
        dto.setRecipientName(entity.getRecipientName());
        dto.setRecipientAccountNumber(entity.getRecipientAccountNumber());

        if ("giro".equalsIgnoreCase(entity.getPaymentMethod())) {
            dto.setAmount(entity.getGiroAmount());
            dto.setFrequency(entity.getGiroFrequency());
            dto.setStartDate(entity.getGiroStartDate());
        } else if ("standing instruction".equalsIgnoreCase(entity.getPaymentMethod())) {
            dto.setAmount(entity.getStandingAmount());
            dto.setFrequency(entity.getFrequency());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
        }
        return dto;
    }

    @Override
    public SearchSchedulePaymentResponseDto searchSchedulePayments(String paymentMethod, String status,
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

        return new SearchSchedulePaymentResponseDto(dtoList, page.getTotalPages());
        // TODO Auto-generated method stub
    }

    @Override
    public int dailyScheduleCheck() {
        List<SchedulePayment> schedules = repository.findByStatusIgnoreCase("processing");
        List<Transfer> transfersToSave = new ArrayList<>();
        int transferCreated = 0;

        Instant now = Instant.now();

        for (SchedulePayment p : schedules) {

            boolean shouldCreateTransfer = false;
            String method = p.getPaymentMethod();
            boolean isGiro = "giro".equalsIgnoreCase(method);

            Instant start = isGiro ? p.getGiroStartDate() : p.getStartDate();
            Instant end = p.getEndDate() != null ? p.getEndDate() : Instant.now();

            long daysBetweenStart = ChronoUnit.DAYS.between(start.truncatedTo(ChronoUnit.DAYS), now.truncatedTo(ChronoUnit.DAYS));
            long daysBetweenEnd = ChronoUnit.DAYS.between(now.truncatedTo(ChronoUnit.DAYS), end.truncatedTo(ChronoUnit.DAYS));

            if (isGiro) {
                if (daysBetweenStart == 0) {
                    shouldCreateTransfer = true;
                }
            } else {
                if (daysBetweenStart >= 0 && daysBetweenEnd >= 0) {
                    shouldCreateTransfer = true;
                }
            }

            // Giro: 4
            // Standing: 5
            Long paymentMethodId = isGiro ? 4L : 5L;

            if (shouldCreateTransfer) {
                transferService.create(p.getUser().getId(),
                        new CreateTransferRequestDto(
                                isGiro ? p.getGiroAmount() : p.getStandingAmount(),
                                paymentMethodId,
                                p.getRecipientName(),
                                "",
                                "",
                                p.getRecipientAccountNumber(),
                                "",
                                "",
                                "",
                                "",
                                Instant.now()
                        )
                );
                transferCreated++;
            }
        }

        return transferCreated;
    }


}
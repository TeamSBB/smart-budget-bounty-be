package com.smartbudgetbounty.service.transfer;

import com.smartbudgetbounty.dto.notification.CreateNotificationDtoRequest;
import com.smartbudgetbounty.dto.transfer.*;
import com.smartbudgetbounty.entity.PaymentMethod;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.enums.NotificationType;
import com.smartbudgetbounty.repository.TransferRepository;
import com.smartbudgetbounty.service.notification.NotificationService;
import com.smartbudgetbounty.service.paymentmethod.PaymentMethodService;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final PaymentMethodService paymentMethodService;
    private final RewardPointsTransactionService pointsTransactionService;
    private final NotificationService notificationService;

    public TransferServiceImpl(
            UserService userService,
            TransferRepository transferRepository,
            PaymentMethodService paymentMethodService,
            RewardPointsTransactionService pointsTransactionService,
            NotificationService notificationService
    ) {
        super();
        this.userService = userService;
        this.transferRepository = transferRepository;
        this.paymentMethodService = paymentMethodService;
        this.pointsTransactionService = pointsTransactionService;
        this.notificationService = notificationService;
    }

    // helper methods

    // save Transfer to TransferRepository
    private Transfer save(Transfer transfer) {
        LogUtil.logStart(logger, "Saving Transfer.");

        transfer = transferRepository.save(transfer);

        LogUtil.logEnd(logger, "Saved Transfer: {}", transfer);

        return transfer;
    }

    // convert Transfer to TransferResponseDto
    private TransferResponseDto toTransferResponseDto(Transfer transfer) {
        return new TransferResponseDto(
                transfer.getId(),
                transfer.getRecipientName(),
                transfer.getFromPaynowPhoneNumber(),
                transfer.getToPaynowPhoneNumber(),
                transfer.getTransferDate(),
                transfer.getBeneficiaryName(),
                transfer.getFromAccountNumber(),
                transfer.getToAccountNumber(),
                transfer.getAmount(),
                transfer.getCreatedAt(),
                transfer.getPaymentMethod().getId(),
                transfer.getRemarks(),
                transfer.getPointsTransaction().getAmount()
        );
    }

    // convert a list of Transfers to a list of TransferResponseDtos
    private List<TransferResponseDto> toTransferResponseDtos(
            List<Transfer> transfers
    ) {
        ArrayList<TransferResponseDto> transferResponseDtos = new ArrayList<TransferResponseDto>();

        for (Transfer transfer : transfers) {
            transferResponseDtos.add(
                    toTransferResponseDto(transfer)
            );
        }

        return transferResponseDtos;
    }

    // service methods

    // create a Transfer and associated RewardPointsTransaction
    // persist Transfer, which persists RewardPointsTransaction via cascade
    // - to be called by TransferController
    @Transactional
    @Override
    public TransferResponseDto create(Long userId, CreateTransferRequestDto requestDto) {
        LogUtil.logStart(logger, "Creating Transfer.");

        // create Transfer
        User user = userService.getById(userId);
        PaymentMethod paymentMethod = paymentMethodService.getById(requestDto.getPaymentMethodId());

        Transfer transfer = new Transfer(
                requestDto.getAmount(),
                Instant.now(),
                requestDto.getRecipientName(),
                requestDto.getFromPaynowPhoneNumber(),
                requestDto.getToPaynowPhoneNumber(),
                requestDto.getFromAccountNumber(),
                requestDto.getToAccountNumber(),
                requestDto.getBeneficiaryName(),
                requestDto.getRemarks(),
                requestDto.getTransferDate() != null ? requestDto.getTransferDate() : Instant.now(),
                user,
                paymentMethod
        );

        // create and persist RewardPointsTransaction
        RewardPointsTransaction pointsTransaction = pointsTransactionService.createEarn(
                user,
                transfer
        );

        // set the relationship from Transfer to RewardPointsTransaction
        transfer.setPointsTransaction(pointsTransaction);

        // persist Transfer, which persists RewardPointsTransaction via cascade
        transfer = save(transfer);
        String transferTo = "";
        String message = "";
        if (transfer.getPaymentMethod().getPaymentMethodName().equals("PayNow")) {
            transferTo = transfer.getRecipientName() + " via PayNow.";
            message = String.format("Transferred $%.2f to %s", transfer.getAmount(), transferTo);
        } else if (transfer.getPaymentMethod().getPaymentMethodName().equals("Credit / Debit Card")) {
            transferTo = transfer.getRecipientName() + " via Credit / Debit Card.";
            message = String.format("Transferred $%.2f to %s", transfer.getAmount(), transferTo);
        } else if (transfer.getPaymentMethod().getPaymentMethodName().equals("Bank Transfer")) {
            transferTo = transfer.getBeneficiaryName() + " via Bank transfer.";
            message = String.format("Transferred $%.2f to %s", transfer.getAmount(), transferTo);
        } else if (transfer.getPaymentMethod().getPaymentMethodName().equals("Giro")) {
            transferTo = transfer.getBeneficiaryName() + " via Giro.";
            message = String.format("Transferred $%.2f %s", transfer.getAmount(), transferTo);
        } else {
            transferTo = transfer.getBeneficiaryName() + " via Standing Instruction.";
            message = String.format("Transferred $%.2f %s", transfer.getAmount(), transferTo);
        }

        notificationService.createNotification(new CreateNotificationDtoRequest(userId, message, requestDto.getTransferDate(), NotificationType.INFO));

        LogUtil.logEnd(logger, "Created Transfer: {}", transfer);

        return toTransferResponseDto(transfer);
    }

    // retrieve a Transfer from TransferRepository
    // - to be called by other service methods
    @Override
    public Transfer getById(Long id) {
        LogUtil.logStart(logger, "Retrieving Transfer by id.");

        Transfer transfer = transferRepository.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transferId: {}.", id);
            return new EntityNotFoundException("Unable to find transferId: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved Transfer: {}", transfer);

        return transfer;
    }

    // retrieve a Transfer from TransferRepository as a TransferResponseDto
    // - to be called by TransferController
    @Override
    public TransferResponseDto getDtoById(Long id) {
        Transfer transfer = getById(id);
        TransferResponseDto transferResponseDto = toTransferResponseDto(transfer);
        return transferResponseDto;
    }

    // retrieve a user's list of Transfers from TransferRepository
    // - to be called by other service methods
    @Override
    public List<Transfer> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Retrieving Transfers by id.");

        User user = userService.getById(userId);
        List<Transfer> transfers = user.getTransfers();

        LogUtil.logEnd(logger, "Retrieved Transfers: {}", transfers);

        return transfers;
    }

    // retrieve a user's list of Transfers from TransferRepository as a list of TransferResponseDtos
    // - to be called by TransferController
    @Override
    public List<TransferResponseDto> getDtosByUserId(Long userId) {
        List<Transfer> transfers = getByUserId(userId);
        List<TransferResponseDto> transferResponseDtos = toTransferResponseDtos(transfers);
        return transferResponseDtos;
    }

    @Override
    public SearchTransferResponseDto searchTransfersByUserId(Long userId,
                                                             SearchTransferRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSorts() == null
                ? List.of()
                : requestDto.getSorts()
                .stream()
                .map(s -> "desc".equalsIgnoreCase(s.getDirection())
                        ? Sort.Order.desc(s.getField())
                        : Sort.Order.asc(s.getField()))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(), orders.isEmpty() ? Sort.unsorted() : Sort.by(orders));
        Page<Transfer> transferPages = transferRepository.searchTransfers(
                userId,
                requestDto.getRecipientName(),
                requestDto.getPaymentMethodId(),
                requestDto.getStartSearchDate(),
                pageable);

        List<SearchTransferDto> responseDtos = new ArrayList<>();


        List<Transfer> sortedTransfers = transferPages.getContent().stream()
                .sorted(Comparator.comparing(Transfer::getCreatedAt).reversed())
                .toList();

        for (Transfer x : sortedTransfers) {
            responseDtos.add(
                    new SearchTransferDto(
                            x.getId(),
                            x.getRecipientName(),
                            x.getFromPaynowPhoneNumber(),
                            x.getToPaynowPhoneNumber(),
                            x.getTransferDate(),
                            x.getBeneficiaryName(),
                            x.getFromAccountNumber(),
                            x.getToAccountNumber(),
                            x.getAmount(),
                            x.getCreatedAt(),
                            x.getPaymentMethod().getPaymentMethodName(),
                            x.getRemarks(),
                            x.getPointsTransaction().getAmount()
                    )
            );
        }

        return new SearchTransferResponseDto(responseDtos, transferPages.getTotalPages());
    }
}

package com.smartbudgetbounty.service.transfer;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.CreateTransferDtoResponse;
import com.smartbudgetbounty.entity.PaymentMethod;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.PaymentMethodRepository;
import com.smartbudgetbounty.repository.TransferRepository;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final PaymentMethodRepository paymentMethodRepo;
    private final RewardPointsTransactionService pointsTransactionService;

    public TransferServiceImpl(
        UserService userService,
        TransferRepository transferRepository,
        PaymentMethodRepository paymentMethodRepo,
        RewardPointsTransactionService pointsTransactionService
    ) {
        this.userService = userService;
        this.transferRepository = transferRepository;
        this.paymentMethodRepo = paymentMethodRepo;
        this.pointsTransactionService = pointsTransactionService;
    }

    @Override
    public CreateTransferDtoResponse create(CreateTransferDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transaction.");

        // get User from repository
        User user = userService.getById(request.getUserId());

        // get PaymentMethod from repository
        Optional<PaymentMethod> paymentMethod = paymentMethodRepo.findById(
            request.getPaymentMethodId()
        );

        if (paymentMethod.isEmpty()) {
            LogUtil.logError(logger, "Unable to find paymentId: {}.", request.getPaymentMethodId());
            throw new EntityNotFoundException(
                "Unable to find paymentId: " + request.getPaymentMethodId()
            );
        }

        // create and persist Transfer
        Instant now = Instant.now();
        Transfer transfer = transferRepository.save(
            new Transfer(
                request.getTransactionAmount(),
                now,
                request.getRecipientName(),
                paymentMethod.get(),
                request.getPaynowPhoneNumber(),
                request.getAccountNumber(),
                request.getRemarks(),
                request.getBankName(),
                request.getBeneficiaryName(),
                request.getTransferDate() != null ? request.getTransferDate() : Instant.now(),
                user
            )
        );

        // create and persist RewardPointsTransaction
        pointsTransactionService.createEarn(user, transfer);

        LogUtil.logEnd(logger, "Created Transaction: {}", transfer);

        return new CreateTransferDtoResponse(
            transfer.getId(),
            transfer.getTransactionAmount(),
            transfer.getRecipientName(),
            request.getPaymentMethodId(),
            now,
            request.getTransferDate() != null ? request.getTransferDate() : now,
            transfer.getPaynowPhoneNumber(),
            transfer.getAccountNumber(),
            transfer.getRemarks(),
            transfer.getBankName(),
            transfer.getBeneficiaryName()
        );
    }

    // retrieve Transaction from TransactionRepository
    // - to be called by other services
    @Override
    public Transfer getById(Long id) {
        LogUtil.logStart(logger, "Getting Transaction by id.");

        Transfer transfer = transferRepository.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transactionId: {}.", id);
            return new EntityNotFoundException("Unable to find transactionId: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved Transaction: {}", transfer);

        return transfer;
    }
}

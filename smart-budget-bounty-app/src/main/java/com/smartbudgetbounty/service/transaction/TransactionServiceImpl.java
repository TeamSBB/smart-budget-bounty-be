package com.smartbudgetbounty.service.transaction;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoRequest;
import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoResponse;
import com.smartbudgetbounty.entity.Transaction;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.TransactionRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final UserRepository userRepo;
    private final TransactionRepository transactionRepo;
    private final RewardPointsTransactionService rewardPointsTransactionService;

    public TransactionServiceImpl(
        UserRepository userRepo,
        TransactionRepository transactionRepo,
        RewardPointsTransactionService rewardPointsTransactionService
    ) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.rewardPointsTransactionService = rewardPointsTransactionService;
    }

    @Override
    public CreateTransactionDtoResponse create(CreateTransactionDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transaction.");

        // get user from repository
        User user = userRepo.findById(request.getUserId()).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find userId: {}.", request.getUserId());
            return new EntityNotFoundException("Unable to find userId: " + request.getUserId());
        });

        // create and persist transaction
        Instant now = Instant.now();
        Transaction transaction = transactionRepo.save(
            new Transaction(
                request.getTransactionAmount(),
                now,
                request.getRecipientName(),
                request.getPaymentMethod(),
                request.getPaynowRecipient(),
                request.getAccountNumber(),
                request.getRemarks(),
                request.getTransferDate() != null ? request.getTransferDate() : Instant.now(),
                user
            )
        );

        // TODO: call RewardPointsTransactionServiceImpl.createEarn
        rewardPointsTransactionService.createEarn(user, transaction);

        LogUtil.logEnd(logger, "Created Transaction: {}", transaction);

        return new CreateTransactionDtoResponse(
            transaction.getId(),
            transaction.getTransactionAmount(),
            transaction.getRecipientName(),
            now,
            request.getTransferDate() != null ? request.getTransferDate() : now,
            transaction.getPaymentMethod(),
            transaction.getPaynowRecipient(),
            transaction.getAccountNumber(),
            transaction.getRemarks()
        );
    }
}

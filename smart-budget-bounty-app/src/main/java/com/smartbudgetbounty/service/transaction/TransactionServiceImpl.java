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
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final RewardPointsTransactionService rewardPointsTransactionService;

    public TransactionServiceImpl(
        UserService userService,
        TransactionRepository transactionRepo,
        RewardPointsTransactionService rewardPointsTransactionService
    ) {
        this.userService = userService;
        this.transactionRepository = transactionRepo;
        this.rewardPointsTransactionService = rewardPointsTransactionService;
    }

    // create and persist Transaction
    @Override
    public CreateTransactionDtoResponse create(CreateTransactionDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transaction.");

        // get user from repository
        User user = userService.getById(request.getUserId());

        // create and persist Transaction
        Instant now = Instant.now();
        Transaction transaction = transactionRepository.save(
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

        // create and persist RewardPointsTransaction
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

    @Override
    public Transaction getById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transactionId: {}.", id);
            return new EntityNotFoundException("Unable to find transactionId: " + id);
        });
        return transaction;
    }
}

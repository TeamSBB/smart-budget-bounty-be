package com.smartbudgetbounty.service.rewardpointtransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.rewardpointtransaction.CreateRewardPointTransactionDtoRequest;
import com.smartbudgetbounty.dto.rewardpointtransaction.RewardPointTransactionDtoResponse;
import com.smartbudgetbounty.entity.RewardPointTransaction;
import com.smartbudgetbounty.entity.Transaction;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.RewardPointTransactionRepository;
import com.smartbudgetbounty.repository.TransactionRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RewardPointTransactionServiceImpl implements RewardPointTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointTransactionServiceImpl.class
    );

    private final RewardPointTransactionRepository rewardPointTransactionRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public RewardPointTransactionServiceImpl(
        RewardPointTransactionRepository rewardPointTransactionRepository,
        UserRepository userRepository,
        TransactionRepository transactionRepository
    ) {
        this.rewardPointTransactionRepository = rewardPointTransactionRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    RewardPointTransactionDtoResponse
        toRewardPointTransactionDtoResponse(RewardPointTransaction rewardPointTransaction) {
        return new RewardPointTransactionDtoResponse(
            rewardPointTransaction.getId(),
            rewardPointTransaction.getAmount(),
            rewardPointTransaction.getDate(),
            rewardPointTransaction.getUser().getId(),
            rewardPointTransaction.getTransaction().getId()
        );
    }

    @Override
    public RewardPointTransactionDtoResponse getById(Long id) {
        LogUtil.logStart(logger, "Getting RewardPointTransaction by id.");

        RewardPointTransaction rewardPointTransaction = rewardPointTransactionRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardPointTransaction not found for id: {}", id);
            return new EntityNotFoundException("RewardPointTransaction not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardPointTransaction: {}", rewardPointTransaction);

        return toRewardPointTransactionDtoResponse(rewardPointTransaction);
    }

    @Override
    public List<RewardPointTransactionDtoResponse> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Getting list of RewardPointTransaction by userId.");

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            throw new EntityNotFoundException("Unable to find userId: " + userId);
        }

        List<RewardPointTransaction> rewardPointTransactions = rewardPointTransactionRepository.findByUserId(
            user.get().getId()
        );

        ArrayList<RewardPointTransactionDtoResponse> rewardPointTransactionDtos = new ArrayList<RewardPointTransactionDtoResponse>();

        for (RewardPointTransaction rewardPointTransaction : rewardPointTransactions) {
            rewardPointTransactionDtos.add(
                toRewardPointTransactionDtoResponse(rewardPointTransaction)
            );
        }

        LogUtil.logEnd(
            logger,
            "Retrieved RewardPointTransactions for userId {}: {}",
            user.get().getId(),
            rewardPointTransactionDtos
        );

        return rewardPointTransactionDtos;
    }

    @Override
    public
        RewardPointTransactionDtoResponse
        create(CreateRewardPointTransactionDtoRequest request) {
        LogUtil.logStart(logger, "Creating RewardPointTransaction.");

        Optional<User> user = userRepository.findById(request.getUserId());

        if (user.isEmpty()) {
            LogUtil.logError(logger, "Unable to find userId: {}.", request.getUserId());
            throw new EntityNotFoundException("Unable to find userId: " + request.getUserId());
        }

        Optional<Transaction> transaction = transactionRepository.findById(
            request.getTransactionId()
        );

        if (transaction.isEmpty()) {
            LogUtil.logError(
                logger,
                "Unable to find transactionId: {}.",
                request.getTransactionId()
            );
            throw new EntityNotFoundException(
                "Unable to find transactionId: " + request.getTransactionId()
            );
        }

        Instant now = Instant.now();

        RewardPointTransaction rewardPointTransaction = rewardPointTransactionRepository.save(
            new RewardPointTransaction(request.getAmount(), now, user.get(), transaction.get())
        );

        LogUtil.logEnd(logger, "Created RewardPointTransaction: {}", rewardPointTransaction);

        return toRewardPointTransactionDtoResponse(rewardPointTransaction);
    }

}

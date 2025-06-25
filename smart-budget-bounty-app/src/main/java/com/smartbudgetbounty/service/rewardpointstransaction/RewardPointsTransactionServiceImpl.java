package com.smartbudgetbounty.service.rewardpointstransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transaction;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.enums.RewardPointsTransactionType;
import com.smartbudgetbounty.repository.RewardPointsTransactionRepository;
import com.smartbudgetbounty.repository.TransactionRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RewardPointsTransactionServiceImpl implements RewardPointsTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionServiceImpl.class
    );

    private final RewardPointsTransactionRepository rewardPointsTransactionRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public RewardPointsTransactionServiceImpl(
        RewardPointsTransactionRepository rewardPointsTransactionRepository,
        UserRepository userRepository,
        TransactionRepository transactionRepository
    ) {
        this.rewardPointsTransactionRepository = rewardPointsTransactionRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    // helper methods

    RewardPointsTransactionResponseDto
        toRewardPointsTransactionResponseDto(RewardPointsTransaction rewardPointsTransaction) {
        return new RewardPointsTransactionResponseDto(
            rewardPointsTransaction.getId(),
            rewardPointsTransaction.getPointsTransactionType().name(),
            rewardPointsTransaction.getAmount(),
            rewardPointsTransaction.getPointsTransactionDate(),
            rewardPointsTransaction.getUser().getId(),
            rewardPointsTransaction.getTransaction().getId(),
            rewardPointsTransaction.getRewardVoucher().getId()
        );
    }

    int toRewardPointsAmount(Double transactionAmount) {
        return (int) Math.floor(transactionAmount);
    }

    // service methods

    // method to be called whenever a Transaction is created
    @Override
    public RewardPointsTransactionResponseDto createEarn(Long userId, Long transactionId) {
        LogUtil.logStart(logger, "Creating EARNED RewardPointsTransaction.");

        // get user from repository
        User user = userRepository.findById(userId).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            return new EntityNotFoundException("Unable to find userId: " + userId);
        });

        // get transaction from repository
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transactionId: {}.", transactionId);
            return new EntityNotFoundException("Unable to find transactionId: " + transactionId);
        });

        // create rewardPointsTransaction
        RewardPointsTransaction rewardPointsTransaction = new RewardPointsTransaction(
            RewardPointsTransactionType.EARN,
            toRewardPointsAmount(transaction.getTransactionAmount()),
            Instant.now(),
            user
        );

        // set bidirectional relationship between RewardPointsTransaction and Transaction
        rewardPointsTransaction.setTransaction(transaction);
        transaction.setRewardPointsTransaction(rewardPointsTransaction);

        // persist RewardPointsTransaction and Transaction
        rewardPointsTransactionRepository.save(rewardPointsTransaction);
        transactionRepository.save(transaction);

        LogUtil.logEnd(
            logger,
            "Created EARNED RewardPointsTransaction: {}",
            rewardPointsTransaction
        );

        return toRewardPointsTransactionResponseDto(rewardPointsTransaction);
    }

    @Override
    public RewardPointsTransactionResponseDto createRedeem(Long userId, int redeemAmount) {
        LogUtil.logStart(logger, "Creating REDEEM RewardPointsTransaction.");

        // get user from repository
        User user = userRepository.findById(userId).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            return new EntityNotFoundException("Unable to find userId: " + userId);
        });

        // create rewardPointsTransaction
        RewardPointsTransaction rewardPointsTransaction = new RewardPointsTransaction(
            RewardPointsTransactionType.REDEEM,
            redeemAmount,
            Instant.now(),
            user
        );

        // persist RewardPointsTransaction
        rewardPointsTransactionRepository.save(rewardPointsTransaction);

        // TODO: call RewardVoucherServiceImpl.create

        LogUtil.logEnd(
            logger,
            "Created REDEEM RewardPointsTransaction: {}",
            rewardPointsTransaction
        );

        return toRewardPointsTransactionResponseDto(rewardPointsTransaction);
    }

    @Override
    public List<RewardPointsTransactionResponseDto> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Getting list of RewardPointsTransaction by userId.");

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            throw new EntityNotFoundException("Unable to find userId: " + userId);
        }

        List<RewardPointsTransaction> rewardPointsTransactions = rewardPointsTransactionRepository.findByUserId(
            user.get().getId()
        );

        ArrayList<RewardPointsTransactionResponseDto> rewardPointsTransactionDtos = new ArrayList<RewardPointsTransactionResponseDto>();

        for (RewardPointsTransaction rewardPointsTransaction : rewardPointsTransactions) {
            rewardPointsTransactionDtos.add(
                toRewardPointsTransactionResponseDto(rewardPointsTransaction)
            );
        }

        LogUtil.logEnd(
            logger,
            "Retrieved RewardPointsTransactions for userId {}: {}",
            user.get().getId(),
            rewardPointsTransactionDtos
        );

        return rewardPointsTransactionDtos;
    }

    @Override
    public RewardPointsTransactionResponseDto getById(Long id) {
        LogUtil.logStart(logger, "Getting RewardPointsTransaction by id.");

        RewardPointsTransaction rewardPointsTransaction = rewardPointsTransactionRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardPointsTransaction not found for id: {}", id);
            return new EntityNotFoundException("RewardPointsTransaction not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardPointsTransaction: {}", rewardPointsTransaction);

        return toRewardPointsTransactionResponseDto(rewardPointsTransaction);
    }

}

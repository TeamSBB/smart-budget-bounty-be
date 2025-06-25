package com.smartbudgetbounty.service.rewardpointstransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RewardPointsTransactionServiceImpl implements RewardPointsTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionServiceImpl.class
    );

    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final RewardPointsTransactionRepository rewardPointsTransactionRepository;

    public RewardPointsTransactionServiceImpl(
        UserService userService,
        TransactionRepository transactionRepository,
        RewardPointsTransactionRepository rewardPointsTransactionRepository
    ) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
        this.rewardPointsTransactionRepository = rewardPointsTransactionRepository;
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

    Integer toRewardPointsAmount(Double transactionAmount) {
        return (int) Math.floor(transactionAmount);
    }

    // service methods

    // create and persist RewardPointsTransaction
    // - to be called by TransactionService whenever a Transaction is created
    @Override
    public RewardPointsTransactionResponseDto createEarn(User user, Transaction transaction) {
        LogUtil.logStart(logger, "Creating EARN RewardPointsTransaction.");

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

        LogUtil.logEnd(logger, "Created EARN RewardPointsTransaction: {}", rewardPointsTransaction);

        return toRewardPointsTransactionResponseDto(rewardPointsTransaction);
    }

    // create and persist RewardPointsTransaction
    // - to be called by RewardPointsTransactionController
    @Override
    public RewardPointsTransactionResponseDto createRedeem(Long userId, Integer redeemAmount) {
        LogUtil.logStart(logger, "Creating REDEEM RewardPointsTransaction.");

        // get User from repository
        User user = userService.getById(userId);

        // create and persist RewardPointsTransaction
        RewardPointsTransaction rewardPointsTransaction = rewardPointsTransactionRepository.save(
            new RewardPointsTransaction(
                RewardPointsTransactionType.REDEEM,
                redeemAmount,
                Instant.now(),
                user
            )
        );

        // TODO: call RewardVoucherServiceImpl.create to create and persist RewardVoucher

        LogUtil.logEnd(
            logger,
            "Created REDEEM RewardPointsTransaction: {}",
            rewardPointsTransaction
        );

        return toRewardPointsTransactionResponseDto(rewardPointsTransaction);
    }

    // retrieve RewardPointsTransaction
    @Override
    public RewardPointsTransaction getById(Long id) {
        LogUtil.logStart(logger, "Getting RewardPointsTransaction by id.");

        RewardPointsTransaction rewardPointsTransaction = rewardPointsTransactionRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardPointsTransaction not found for id: {}", id);
            return new EntityNotFoundException("RewardPointsTransaction not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardPointsTransaction: {}", rewardPointsTransaction);

        return rewardPointsTransaction;
    }

    // retrieve RewardPointsTransaction and return it as a RewardPointsTransactionResponseDto
    @Override
    public RewardPointsTransactionResponseDto getDtoById(Long id) {
        RewardPointsTransaction rewardPointsTransaction = getById(id);
        return toRewardPointsTransactionResponseDto(rewardPointsTransaction);
    }

    // retrieve a user's RewardPointsTransactions
    // - to be called by RewardPointsTransactionController
    @Override
    public List<RewardPointsTransactionResponseDto> getDtosByUserId(Long userId) {
        LogUtil.logStart(logger, "Getting list of RewardPointsTransaction by userId.");

        // get User from repository
        User user = userService.getById(userId);

        // convert RewardPointsTransactions to RewardPointsTransactionResponseDto
        List<RewardPointsTransaction> rewardPointsTransactions = user.getRewardPointsTransactions();

        ArrayList<RewardPointsTransactionResponseDto> rewardPointsTransactionDtos = new ArrayList<RewardPointsTransactionResponseDto>();

        for (RewardPointsTransaction rewardPointsTransaction : rewardPointsTransactions) {
            rewardPointsTransactionDtos.add(
                toRewardPointsTransactionResponseDto(rewardPointsTransaction)
            );
        }

        LogUtil.logEnd(
            logger,
            "Retrieved RewardPointsTransactions for userId {}: {}",
            user.getId(),
            rewardPointsTransactionDtos
        );

        return rewardPointsTransactionDtos;
    }
}

package com.smartbudgetbounty.service.rewardpointstransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateEarnRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transaction;
import com.smartbudgetbounty.entity.User;
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

    RewardPointsTransactionResponseDto
        toRewardPointsTransactionDtoResponse(RewardPointsTransaction rewardPointsTransaction) {
        return new RewardPointsTransactionResponseDto(
            rewardPointsTransaction.getId(),
            rewardPointsTransaction.getAmount(),
            rewardPointsTransaction.getRewardDate(),
            rewardPointsTransaction.getUser().getId(),
            rewardPointsTransaction.getTransaction().getId()
        );
    }

    @Override
    public
        RewardPointsTransactionResponseDto
        create(Long userId, CreateEarnRewardPointsTransactionRequestDto request) {
        LogUtil.logStart(logger, "Creating RewardPointsTransaction.");

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            throw new EntityNotFoundException("Unable to find userId: " + userId);
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

        RewardPointsTransaction rewardPointsTransaction = rewardPointsTransactionRepository.save(
            new RewardPointsTransaction(request.getAmount(), now, user.get(), transaction.get())
        );

        LogUtil.logEnd(logger, "Created RewardPointsTransaction: {}", rewardPointsTransaction);

        return toRewardPointsTransactionDtoResponse(rewardPointsTransaction);
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
                toRewardPointsTransactionDtoResponse(rewardPointsTransaction)
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

        return toRewardPointsTransactionDtoResponse(rewardPointsTransaction);
    }

}

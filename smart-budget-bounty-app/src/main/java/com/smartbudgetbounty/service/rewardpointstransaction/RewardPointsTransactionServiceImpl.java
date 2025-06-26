package com.smartbudgetbounty.service.rewardpointstransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateRewardPointsTransactionDtoRequest;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionDtoResponse;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.RewardPointsTransactionRepository;
import com.smartbudgetbounty.repository.TransferRepository;
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
    private final TransferRepository transactionRepository;

    public RewardPointsTransactionServiceImpl(
        RewardPointsTransactionRepository rewardPointsTransactionRepository,
        UserRepository userRepository,
        TransferRepository transactionRepository
    ) {
        this.rewardPointsTransactionRepository = rewardPointsTransactionRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    RewardPointsTransactionDtoResponse
        toRewardPointsTransactionDtoResponse(RewardPointsTransaction rewardPointsTransaction) {
        return new RewardPointsTransactionDtoResponse(
            rewardPointsTransaction.getId(),
            rewardPointsTransaction.getAmount(),
            rewardPointsTransaction.getDate(),
            rewardPointsTransaction.getUser().getId(),
            rewardPointsTransaction.getTransaction().getId()
        );
    }

    @Override
    public
        RewardPointsTransactionDtoResponse
        create(Long userId, CreateRewardPointsTransactionDtoRequest request) {
        LogUtil.logStart(logger, "Creating RewardPointsTransaction.");

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            throw new EntityNotFoundException("Unable to find userId: " + userId);
        }

        Optional<Transfer> transaction = transactionRepository.findById(
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
    public List<RewardPointsTransactionDtoResponse> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Getting list of RewardPointsTransaction by userId.");

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            LogUtil.logError(logger, "Unable to find userId: {}.", userId);
            throw new EntityNotFoundException("Unable to find userId: " + userId);
        }

        List<RewardPointsTransaction> rewardPointsTransactions = rewardPointsTransactionRepository.findByUserId(
            user.get().getId()
        );

        ArrayList<RewardPointsTransactionDtoResponse> rewardPointsTransactionDtos = new ArrayList<RewardPointsTransactionDtoResponse>();

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
    public RewardPointsTransactionDtoResponse getById(Long id) {
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

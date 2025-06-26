package com.smartbudgetbounty.service.rewardpointstransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateRedeemRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.enums.RewardPointsTransactionType;
import com.smartbudgetbounty.repository.RewardPointsTransactionRepository;
import com.smartbudgetbounty.repository.TransferRepository;
import com.smartbudgetbounty.service.rewardvoucher.RewardVoucherService;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RewardPointsTransactionServiceImpl implements RewardPointsTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionServiceImpl.class
    );

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final RewardPointsTransactionRepository pointsTransactionRepository;
    private final RewardVoucherService voucherService;

    public RewardPointsTransactionServiceImpl(
        UserService userService,
        TransferRepository transferRepository,
        RewardPointsTransactionRepository pointsTransactionRepository,
        RewardVoucherService voucherService
    ) {
        super();
        this.userService = userService;
        this.transferRepository = transferRepository;
        this.pointsTransactionRepository = pointsTransactionRepository;
        this.voucherService = voucherService;
    }

    // helper methods

    // convert RewardPointsTransaction to RewardPointsTransactionResponseDto
    private RewardPointsTransactionResponseDto toRewardPointsTransactionResponseDto(
        RewardPointsTransaction pointsTransaction
    ) {
        return new RewardPointsTransactionResponseDto(
            pointsTransaction.getId(),
            pointsTransaction.getPointsTransactionType().name(),
            pointsTransaction.getAmount(),
            pointsTransaction.getPointsTransactionDate(),
            pointsTransaction.getUser().getId(),
            pointsTransaction.getTransfer().getId(),
            pointsTransaction.getVoucher().getId()
        );
    }

    // convert a list of RewardPointsTransactions to a list of RewardPointsTransactionResponseDtos
    private List<RewardPointsTransactionResponseDto> toRewardPointsTransactionResponseDtos(
        List<RewardPointsTransaction> pointsTransactions
    ) {
        ArrayList<RewardPointsTransactionResponseDto> pointsTransactionDtos = new ArrayList<RewardPointsTransactionResponseDto>();

        for (RewardPointsTransaction pointsTransaction : pointsTransactions) {
            pointsTransactionDtos.add(
                toRewardPointsTransactionResponseDto(pointsTransaction)
            );
        }

        return pointsTransactionDtos;
    }

    private Integer toRewardPointsAmount(Double transferAmount) {
        return (int) Math.floor(transferAmount);
    }

    // service methods

    // create and persist RewardPointsTransaction
    // update, persist and return Transfer
    // - to be called by TransferService whenever a Transfer is created
    @Override
    public Transfer createEarn(User user, Transfer transfer) {
        LogUtil.logStart(logger, "Creating EARN RewardPointsTransaction.");

        // create rewardPointsTransaction
        RewardPointsTransaction pointsTransaction = new RewardPointsTransaction(
            RewardPointsTransactionType.EARN,
            toRewardPointsAmount(transfer.getTransactionAmount()),
            Instant.now(),
            user
        );

        // set bidirectional relationship between RewardPointsTransaction and Transfer
        pointsTransaction.setTransfer(transfer);
        transfer.setPointsTransaction(pointsTransaction);

        // persist RewardPointsTransaction and Transfer
        pointsTransaction = pointsTransactionRepository.save(pointsTransaction);
        transfer = transferRepository.save(transfer);

        LogUtil.logEnd(logger, "Created EARN RewardPointsTransaction: {}", pointsTransaction);

        return transfer;
    }

    // create and persist RewardPointsTransaction
    // create and persist RewardVoucher
    // - to be called by RewardPointsTransactionController
    @Override
    public RewardPointsTransactionResponseDto createRedeem(
        Long userId,
        CreateRedeemRewardPointsTransactionRequestDto requestDto
    ) {
        LogUtil.logStart(logger, "Creating REDEEM RewardPointsTransaction.");

        // get User from repository
        User user = userService.getById(userId);

        // create and persist RewardPointsTransaction
        RewardPointsTransaction pointsTransaction = pointsTransactionRepository.save(
            new RewardPointsTransaction(
                RewardPointsTransactionType.REDEEM,
                requestDto.getRedeemAmount(),
                Instant.now(),
                user
            )
        );

        // create and persist RewardVoucher, update and persist RewardPointsTransaction
        pointsTransaction = voucherService.create(user, pointsTransaction);

        LogUtil.logEnd(logger, "Created REDEEM RewardPointsTransaction: {}", pointsTransaction);

        return toRewardPointsTransactionResponseDto(pointsTransaction);
    }

    // retrieve RewardPointsTransaction from RewardPointsTransactionRepository
    // - to be called by other services
    @Override
    public RewardPointsTransaction getById(Long id) {
        LogUtil.logStart(logger, "Getting RewardPointsTransaction by id.");

        RewardPointsTransaction pointsTransaction = pointsTransactionRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardPointsTransaction not found for id: {}", id);
            return new EntityNotFoundException("RewardPointsTransaction not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardPointsTransaction: {}", pointsTransaction);

        return pointsTransaction;
    }

    // retrieve RewardPointsTransaction from RewardPointsTransactionRepository and return it as a
    // RewardPointsTransactionResponseDto
    // - to be called by RewardPointsTransactionController
    @Override
    public RewardPointsTransactionResponseDto getDtoById(Long id) {
        RewardPointsTransaction pointsTransaction = getById(id);
        return toRewardPointsTransactionResponseDto(pointsTransaction);
    }

    // retrieve a user's RewardPointsTransactions from RewardPointsTransactions and return it as a
    // RewardPointsTransactionResponseDto
    // - to be called by RewardPointsTransactionController
    @Override
    public List<RewardPointsTransactionResponseDto> getDtosByUserId(Long userId) {
        LogUtil.logStart(logger, "Getting list of RewardPointsTransaction by userId.");

        // get User from repository
        User user = userService.getById(userId);

        // convert RewardPointsTransactions to RewardPointsTransactionResponseDto
        List<RewardPointsTransaction> pointsTransactions = user.getPointsTransactions();
        List<RewardPointsTransactionResponseDto> pointsTransactionDtos = toRewardPointsTransactionResponseDtos(
            pointsTransactions
        );

        LogUtil.logEnd(
            logger,
            "Retrieved RewardPointsTransactions: {}",
            pointsTransactionDtos
        );

        return pointsTransactionDtos;
    }
}

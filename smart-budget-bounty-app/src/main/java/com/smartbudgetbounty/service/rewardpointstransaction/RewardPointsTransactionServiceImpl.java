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
import com.smartbudgetbounty.entity.RewardVoucher;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.enums.RewardPointsTransactionType;
import com.smartbudgetbounty.enums.RewardVoucherType;
import com.smartbudgetbounty.repository.RewardPointsTransactionRepository;
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
    private final RewardPointsTransactionRepository pointsTransactionRepository;
    private final RewardVoucherService voucherService;

    public RewardPointsTransactionServiceImpl(
        UserService userService,
        RewardPointsTransactionRepository pointsTransactionRepository,
        RewardVoucherService voucherService
    ) {
        super();
        this.userService = userService;
        this.pointsTransactionRepository = pointsTransactionRepository;
        this.voucherService = voucherService;
    }

    // helper methods

    // calculate the amount of reward points earned from a Transfer
    private Integer toRewardPointsAmount(Transfer transfer) {
        return (int) Math.floor(transfer.getAmount());
    }

    // save RewardPointsTransaction to RewardPointsTransactionRepository
    private RewardPointsTransaction save(RewardPointsTransaction pointsTransaction) {
        LogUtil.logStart(logger, "Saving RewardPointsTransaction.");

        pointsTransaction = pointsTransactionRepository.save(pointsTransaction);

        LogUtil.logEnd(logger, "Saved RewardPointsTransaction: {}", pointsTransaction);

        return pointsTransaction;
    }

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
            pointsTransaction.getTransfer()
                != null ? pointsTransaction.getTransfer().getId() : null,
            pointsTransaction.getVoucher()
                != null ? pointsTransaction.getVoucher().getId() : null
        );
    }

    // convert a list of RewardPointsTransactions to a list of
    // RewardPointsTransactionResponseDtos
    private List<RewardPointsTransactionResponseDto> toRewardPointsTransactionResponseDtos(
        List<RewardPointsTransaction> pointsTransactions
    ) {
        ArrayList<RewardPointsTransactionResponseDto> pointsTransactionResponseDtos = new ArrayList<RewardPointsTransactionResponseDto>();

        for (RewardPointsTransaction pointsTransaction : pointsTransactions) {
            pointsTransactionResponseDtos.add(
                toRewardPointsTransactionResponseDto(pointsTransaction)
            );
        }

        return pointsTransactionResponseDtos;
    }

    // service methods

    // create an EARN RewardPointsTransaction
    // - to be called by TransferService whenever a Transfer is created
    // - persistence is handled by TransferService via cascade
    @Override
    public RewardPointsTransaction createEarn(User user, Transfer transfer) {
        LogUtil.logStart(logger, "Creating EARN RewardPointsTransaction.");

        // create rewardPointsTransaction
        RewardPointsTransaction pointsTransaction = new RewardPointsTransaction(
            RewardPointsTransactionType.EARN,
            toRewardPointsAmount(transfer),
            Instant.now(),
            user
        );

        // set relationship from RewardPointsTransaction to Transfer
        pointsTransaction.setTransfer(transfer);

        LogUtil.logEnd(logger, "Created EARN RewardPointsTransaction: {}", pointsTransaction);

        return pointsTransaction;
    }

    // create a REDEEM RewardPointsTransaction and associated RewardVoucher
    // persist RewardPointsTransaction, which persists RewardVoucher via cascade
    // - to be called by RewardPointsTransactionController
    @Override
    public RewardPointsTransactionResponseDto createRedeem(
        Long userId,
        CreateRedeemRewardPointsTransactionRequestDto requestDto
    ) {
        LogUtil.logStart(logger, "Creating REDEEM RewardPointsTransaction.");

        // check if user has sufficient points
        Integer pointsBalance = getBalance(userId);

        if (pointsBalance < requestDto.getRedeemAmount()) {
            LogUtil.logError(
                logger,
                "User has insufficient points: redeem amount ({}) exceeds points balance ({}).",
                requestDto.getRedeemAmount(),
                pointsBalance

            );
            throw new IllegalStateException(
                String.format(
                    "User has insufficient points: redeem amount (%d) exceeds points balance (%d).",
                    requestDto.getRedeemAmount(),
                    pointsBalance
                )
            );
        }

        // check if request voucherType is valid
        String requestVoucherType = requestDto.getVoucherType();
        RewardVoucherType voucherType;

        try {
            voucherType = RewardVoucherType.valueOf(requestVoucherType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid voucher type: " + requestVoucherType);
        }

        // create RewardPointsTransaction and RewardVoucher
        User user = userService.getById(userId);

        RewardPointsTransaction pointsTransaction = new RewardPointsTransaction(
            RewardPointsTransactionType.REDEEM,
            requestDto.getRedeemAmount() * -1,
            Instant.now(),
            user
        );

        RewardVoucher rewardVoucher = voucherService.create(voucherType, user, pointsTransaction);

        // set relationship from RewardPointsTransaction to RewardVoucher
        pointsTransaction.setVoucher(rewardVoucher);

        // persist RewardPointsTransaction, which persists RewardVoucher via cascade
        pointsTransaction = save(pointsTransaction);

        LogUtil.logEnd(logger, "Created REDEEM RewardPointsTransaction: {}", pointsTransaction);

        return toRewardPointsTransactionResponseDto(pointsTransaction);
    }

    // retrieve a RewardPointsTransaction from RewardPointsTransactionRepository
    // - to be called by other service methods
    @Override
    public RewardPointsTransaction getById(Long id) {
        LogUtil.logStart(logger, "Retrieving RewardPointsTransaction by id.");

        RewardPointsTransaction pointsTransaction = pointsTransactionRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardPointsTransaction not found for id: {}", id);
            return new EntityNotFoundException("RewardPointsTransaction not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardPointsTransaction: {}", pointsTransaction);

        return pointsTransaction;
    }

    // retrieve a RewardPointsTransaction from RewardPointsTransactionRepository as a
    // RewardPointsTransactionResponseDto
    // - to be called by RewardPointsTransactionController
    @Override
    public RewardPointsTransactionResponseDto getDtoById(Long id) {
        RewardPointsTransaction pointsTransaction = getById(id);
        return toRewardPointsTransactionResponseDto(pointsTransaction);
    }

    // retrieve a user's list of RewardPointsTransactions from RewardPointsTransactionRepository
    // - to be called by other service methods
    public List<RewardPointsTransaction> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Retrieving list of RewardPointsTransaction by userId.");

        User user = userService.getById(userId);
        List<RewardPointsTransaction> pointsTransactions = user.getPointsTransactions();

        LogUtil.logEnd(logger, "Retrieved RewardPointsTransactions: {}", pointsTransactions);

        return pointsTransactions;
    }

    // retrieve a user's list of RewardPointsTransactions from RewardPointsTransactionRepository as
    // a list of RewardPointsTransactionResponseDtos
    // - to be called by RewardPointsTransactionController
    @Override
    public List<RewardPointsTransactionResponseDto> getDtosByUserId(Long userId) {
        List<RewardPointsTransaction> pointsTransactions = getByUserId(userId);
        List<RewardPointsTransactionResponseDto> pointsTransactionResponseDtos = toRewardPointsTransactionResponseDtos(
            pointsTransactions
        );
        return pointsTransactionResponseDtos;
    }

    // calculate the user's reward points balance
    // - to be called by RewardPointsTransactionController and other service methods
    public Integer getBalance(Long userId) {
        LogUtil.logStart(logger, "Calculating reward points balance.");

        Integer pointsBalance = 0;

        List<RewardPointsTransaction> pointsTransactions = getByUserId(userId);
        for (RewardPointsTransaction pointsTransaction : pointsTransactions) {
            pointsBalance += pointsTransaction.getAmount();
        }

        LogUtil.logEnd(logger, "Calculated reward points balance: {}", pointsBalance);

        return pointsBalance;
    }
}

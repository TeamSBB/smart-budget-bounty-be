package com.smartbudgetbounty.service.rewardvoucher;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartbudgetbounty.dto.rewardvoucher.RedeemRewardVoucherRequestDto;
import com.smartbudgetbounty.dto.rewardvoucher.RewardVoucherResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.RewardVoucher;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.enums.RewardVoucherStatus;
import com.smartbudgetbounty.repository.RewardPointsTransactionRepository;
import com.smartbudgetbounty.repository.RewardVoucherRepository;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionServiceImpl;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

public class RewardVoucherServiceImpl implements RewardVoucherService {

    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionServiceImpl.class
    );

    private RewardPointsTransactionRepository pointsTransactionRepository;
    private RewardVoucherRepository voucherRepository;

    public RewardVoucherServiceImpl() {
        super();
    }

    // helper methods

    private RewardVoucherResponseDto toRewardVoucherResponseDto(RewardVoucher voucher) {
        return new RewardVoucherResponseDto(
            voucher.getId(),
            voucher.getVoucherStatus().name(),
            voucher.getDiscount(),
            voucher.getEarnDate(),
            voucher.getRedeemDate(),
            voucher.getUser().getId(),
            voucher.getPointsTransaction().getId()
        );
    }

    private Double toRewardVoucherDiscount(Integer pointsTransactionAmount) {
        return pointsTransactionAmount / 100.0;
    }

    // service methods

    // create and persist RewardVoucher
    // - to be called by RewardPointsTransactionService whenever a REDEEM RewardPointsTransaction is
    // created
    public RewardVoucherResponseDto createRewardVoucher(
        User user,
        RewardPointsTransaction pointsTransaction
    ) {
        LogUtil.logStart(logger, "Creating RewardVoucher.");

        // create RewardVoucher
        Double discount = toRewardVoucherDiscount(pointsTransaction.getAmount());
        RewardVoucher voucher = new RewardVoucher(discount, Instant.now(), user);

        // set bidirectional relationship between RewardVoucher and RewardPointsTransaction
        voucher.setPointsTransaction(pointsTransaction);
        pointsTransaction.setRewardVoucher(voucher);

        // persist RewardVoucher and RewardPointsTransaction
        voucher = voucherRepository.save(voucher);
        pointsTransactionRepository.save(pointsTransaction);

        LogUtil.logEnd(logger, "Created RewardVoucher: {}", voucher);

        return toRewardVoucherResponseDto(voucher);
    }

    // retrieve RewardVoucher from RewardVoucherRepository
    // - to be called by other service methods
    public RewardVoucher getById(Long id) {
        LogUtil.logStart(logger, "Getting RewardVoucher by id.");

        RewardVoucher voucher = voucherRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardVoucher not found for id: {}", id);
            return new EntityNotFoundException("RewardVoucher not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardVoucher: {}", voucher);

        return voucher;
    }

    // change the status of a RewardVoucher from AVAILABLE to REDEEMED
    // - to be called by RewardVoucherController
    public RewardVoucherResponseDto redeemRewardVoucher(
        Long userId,
        RedeemRewardVoucherRequestDto requestDto
    ) {
        LogUtil.logStart(logger, "Redeeming RewardVoucher.");

        // retrieve RewardVoucher from repository
        RewardVoucher voucher = getById(requestDto.getVoucherId());

        // update Voucher
        voucher.setVoucherStatus(RewardVoucherStatus.REDEEMED);
        voucher.setRedeemDate(Instant.now());

        // persist Voucher
        voucher = voucherRepository.save(voucher);

        LogUtil.logEnd(logger, "Redeemed RewardVoucher: {}", voucher);

        return toRewardVoucherResponseDto(voucher);
    }
}

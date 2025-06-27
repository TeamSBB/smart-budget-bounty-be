package com.smartbudgetbounty.service.rewardvoucher;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.rewardvoucher.RedeemRewardVoucherRequestDto;
import com.smartbudgetbounty.dto.rewardvoucher.RewardVoucherResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.RewardVoucher;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.enums.RewardVoucherStatus;
import com.smartbudgetbounty.repository.RewardVoucherRepository;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionServiceImpl;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RewardVoucherServiceImpl implements RewardVoucherService {

    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionServiceImpl.class
    );

    private final UserService userService;

    private RewardVoucherRepository voucherRepository;

    public RewardVoucherServiceImpl(UserService userService) {
        super();
        this.userService = userService;
    }

    // helper methods

    // calculate the discount amount for a RewardVoucher obtained from a REDEEM
    // RewardPointsTransaction
    private Double toRewardVoucherDiscount(RewardPointsTransaction pointsTransaction) {
        return pointsTransaction.getAmount() / 100.0 * -1;
    }

    // save RewardVoucher to RewardVoucherRepository
    private RewardVoucher save(RewardVoucher voucher) {
        LogUtil.logStart(logger, "Saving RewardVoucher.");

        voucher = voucherRepository.save(voucher);

        LogUtil.logEnd(logger, "Saved RewardVoucher: {}", voucher);

        return voucher;
    }

    // convert RewardVoucher to RewardVoucherResponseDto
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

    // convert a list of RewardVouchers to a list of RewardVoucherResponseDtos
    private List<RewardVoucherResponseDto> toRewardVoucherResponseDtos(
        List<RewardVoucher> vouchers
    ) {
        ArrayList<RewardVoucherResponseDto> voucherResponseDtos = new ArrayList<RewardVoucherResponseDto>();

        for (RewardVoucher voucher : vouchers) {
            voucherResponseDtos.add(toRewardVoucherResponseDto(voucher));
        }

        return voucherResponseDtos;
    }

    // service methods

    // create a RewardVoucher
    // - to be called by RewardPointsTransactionService whenever a REDEEM
    // RewardPointsTransaction is
    // created
    // - persistence is handled by RewardPointsTransactionService via cascade
    @Override
    public RewardVoucher create(
        User user,
        RewardPointsTransaction pointsTransaction
    ) {
        LogUtil.logStart(logger, "Creating RewardVoucher.");

        // create RewardVoucher
        Double discount = toRewardVoucherDiscount(pointsTransaction);
        RewardVoucher voucher = new RewardVoucher(discount, Instant.now(), user);

        // set relationship from RewardVoucher to RewardPointsTransaction
        voucher.setPointsTransaction(pointsTransaction);

        LogUtil.logEnd(logger, "Created RewardVoucher: {}", voucher);

        return voucher;
    }

    // retrieve a RewardVoucher from RewardVoucherRepository
    // - to be called by other service methods
    @Override
    public RewardVoucher getById(Long id) {
        LogUtil.logStart(logger, "Retrieving RewardVoucher by id.");

        RewardVoucher voucher = voucherRepository.findById(
            id
        ).orElseThrow(() -> {
            LogUtil.logError(logger, "RewardVoucher not found for id: {}", id);
            return new EntityNotFoundException("RewardVoucher not found for id: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved RewardVoucher: {}", voucher);

        return voucher;
    }

    // retrieve a RewardVoucher from RewardVoucherRepository as a RewardVoucherResponseDto
    // - to be called by RewardVoucherController
    public RewardVoucherResponseDto getDtoById(Long voucherId) {
        RewardVoucher voucher = getById(voucherId);
        return toRewardVoucherResponseDto(voucher);
    }

    // retrieve a user's list of RewardVouchers from RewardVoucherRepository
    // - to be called by other services
    public List<RewardVoucher> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Retrieving list of RewardVouchers by userId.");

        User user = userService.getById(userId);
        List<RewardVoucher> vouchers = user.getVouchers();

        LogUtil.logEnd(logger, "Retrieved RewardVouchers: {}", vouchers);

        return vouchers;
    }

    // retrieve a user's list of RewardVouchers from RewardVoucherRepository as a list of
    // RewardVoucherResponseDtos
    // - to be called by RewardVoucherController
    public List<RewardVoucherResponseDto> getDtosByUserId(Long userId) {
        List<RewardVoucher> vouchers = getByUserId(userId);
        List<RewardVoucherResponseDto> rewardVoucherDtos = toRewardVoucherResponseDtos(vouchers);
        return rewardVoucherDtos;
    }

    // update the status of a RewardVoucher from AVAILABLE to REDEEMED
    // - to be called by RewardVoucherController
    @Override
    public RewardVoucherResponseDto redeem(
        Long voucherId,
        RedeemRewardVoucherRequestDto requestDto
    ) {
        LogUtil.logStart(logger, "Redeeming RewardVoucher.");

        // TODO: check if user owns voucher?

        // retrieve RewardVoucher from repository
        RewardVoucher voucher = getById(voucherId);

        // update Voucher
        voucher.setVoucherStatus(RewardVoucherStatus.REDEEMED);
        voucher.setRedeemDate(Instant.now());

        // persist Voucher
        voucher = save(voucher);

        LogUtil.logEnd(logger, "Redeemed RewardVoucher: {}", voucher);

        return toRewardVoucherResponseDto(voucher);
    }
}

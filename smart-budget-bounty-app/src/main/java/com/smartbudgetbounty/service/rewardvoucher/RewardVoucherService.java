package com.smartbudgetbounty.service.rewardvoucher;

import java.util.List;

import com.smartbudgetbounty.dto.rewardvoucher.RedeemRewardVoucherRequestDto;
import com.smartbudgetbounty.dto.rewardvoucher.RewardVoucherResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.RewardVoucher;
import com.smartbudgetbounty.entity.User;

public interface RewardVoucherService {
    // create a RewardVoucher
    // - to be called by RewardPointsTransactionService whenever a REDEEM RewardPointsTransaction is
    // created
    // - persistence is handled by RewardPointsTransactionService via cascade
    RewardVoucher create(
        User user,
        RewardPointsTransaction pointsTransaction
    );

    // retrieve a RewardVoucher from RewardVoucherRepository
    // - to be called by other service methods
    RewardVoucher getById(Long voucherId);

    // retrieve a RewardVoucher from RewardVoucherRepository as a RewardVoucherResponseDto
    // - to be called by RewardVoucherController
    RewardVoucherResponseDto getDtoById(Long voucherId);

    // retrieve a user's list of RewardVouchers from RewardVoucherRepository
    // - to be called by other service methods
    List<RewardVoucher> getByUserId(Long userId);

    // retrieve a user's list of RewardVouchers from RewardVoucherRepository as a list of
    // RewardVoucherResponseDtos
    // - to be called by RewardVoucherController
    List<RewardVoucherResponseDto> getDtosByUserId(Long userId);

    // update the status of a RewardVoucher from AVAILABLE to REDEEMED
    // - to be called by RewardVoucherController
    RewardVoucherResponseDto redeem(Long voucherId, RedeemRewardVoucherRequestDto requestDto);
}

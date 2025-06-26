package com.smartbudgetbounty.service.rewardvoucher;

import com.smartbudgetbounty.dto.rewardvoucher.RedeemRewardVoucherRequestDto;
import com.smartbudgetbounty.dto.rewardvoucher.RewardVoucherResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.RewardVoucher;
import com.smartbudgetbounty.entity.User;

public interface RewardVoucherService {
    // create and persist RewardVoucher
    // - to be called by RewardPointsTransactionService whenever a REDEEM RewardPointsTransaction is
    // created
    RewardVoucherResponseDto createRewardVoucher(
        User user,
        RewardPointsTransaction pointsTransaction
    );

    // retrieve RewardVoucher from RewardVoucherRepository
    // - to be called by other service methods
    RewardVoucher getById(Long voucherId);

    // change the status of a RewardVoucher from AVAILABLE to REDEEMED
    // - to be called by RewardVoucherController
    RewardVoucherResponseDto redeemRewardVoucher(
        Long userId,
        RedeemRewardVoucherRequestDto requestDto
    );
}

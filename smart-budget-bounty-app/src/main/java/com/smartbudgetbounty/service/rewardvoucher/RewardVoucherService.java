package com.smartbudgetbounty.service.rewardvoucher;

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
    RewardPointsTransaction create(
        User user,
        RewardPointsTransaction pointsTransaction
    );

    // retrieve a RewardVoucher from RewardVoucherRepository
    // - to be called by other service methods
    RewardVoucher getById(Long voucherId);

    // update the status of a RewardVoucher from AVAILABLE to REDEEMED
    // - to be called by RewardVoucherController
    RewardVoucherResponseDto redeem(
        Long userId,
        RedeemRewardVoucherRequestDto requestDto
    );
}

package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateRedeemRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;

public interface RewardPointsTransactionService {
    // create an EARN RewardPointsTransaction
    // - to be called by TransferService whenever a Transfer is created
    // - persistence is handled by TransferService via cascade
    Transfer createEarn(User user, Transfer transfer);

    // create a REDEEM RewardPointsTransaction and associated RewardVoucher
    // persist RewardPointsTransaction, which persists RewardVoucher via cascade
    // - to be called by RewardPointsTransactionController
    RewardPointsTransactionResponseDto createRedeem(
        Long userId,
        CreateRedeemRewardPointsTransactionRequestDto requestDto
    );

    // retrieve a RewardPointsTransaction from RewardPointsTransactionRepository
    // - to be called by other services
    RewardPointsTransaction getById(Long id);

    // retrieve a RewardPointsTransaction from RewardPointsTransactionRepository as a
    // RewardPointsTransactionResponseDto
    // - to be called by RewardPointsTransactionController
    RewardPointsTransactionResponseDto getDtoById(Long id);

    // retrieve a user's RewardPointsTransactions from RewardPointsTransactionRepository as a list
    // of RewardPointsTransactionResponseDtos
    // - to be called by RewardPointsTransactionController
    List<RewardPointsTransactionResponseDto> getDtosByUserId(Long userId);
}

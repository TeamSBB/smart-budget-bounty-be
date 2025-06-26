package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateRedeemRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;

public interface RewardPointsTransactionService {
    // create and persist RewardPointsTransaction
    // - to be called by TransactionService whenever a Transaction is created
    RewardPointsTransactionResponseDto createEarn(User user, Transfer transaction);

    // create and persist RewardPointsTransaction
    // - to be called by RewardPointsTransactionController
    RewardPointsTransactionResponseDto createRedeem(
        Long userId,
        CreateRedeemRewardPointsTransactionRequestDto requestDto
    );

    // retrieve RewardPointsTransaction from RewardPointsTransactionRepository
    // - to be called by other services
    RewardPointsTransaction getById(Long id);

    // retrieve RewardPointsTransaction from RewardPointsTransactionRepository and return it as a
    // RewardPointsTransactionResponseDto
    // - to be called by RewardPointsTransactionController
    RewardPointsTransactionResponseDto getDtoById(Long id);

    // retrieve a user's RewardPointsTransactions from RewardPointsTransactions and return it as a
    // RewardPointsTransactionResponseDto
    // - to be called by RewardPointsTransactionController
    List<RewardPointsTransactionResponseDto> getDtosByUserId(Long userId);
}

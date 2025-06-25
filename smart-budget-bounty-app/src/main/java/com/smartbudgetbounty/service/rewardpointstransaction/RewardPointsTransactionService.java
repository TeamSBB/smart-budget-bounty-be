package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transaction;
import com.smartbudgetbounty.entity.User;

public interface RewardPointsTransactionService {
    // to be called by TransactionService whenever a Transaction is created
    RewardPointsTransactionResponseDto createEarn(User user, Transaction transaction);

    // to be called by RewardPointsTransactionController
    RewardPointsTransactionResponseDto createRedeem(Long userId, Integer redeemAmount);

    RewardPointsTransaction getById(Long id);

    RewardPointsTransactionResponseDto getDtoById(Long id);

    // to be called by RewardPointsTransactionController
    List<RewardPointsTransactionResponseDto> getDtosByUserId(Long userId);
}

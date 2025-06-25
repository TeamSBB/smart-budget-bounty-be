package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;

public interface RewardPointsTransactionService {
    RewardPointsTransactionResponseDto createEarn(Long userId, Long transactionId);

    RewardPointsTransactionResponseDto createRedeem(Long userId, int redeemAmount);

    List<RewardPointsTransactionResponseDto> getByUserId(Long userId);

    RewardPointsTransactionResponseDto getById(Long id);
}

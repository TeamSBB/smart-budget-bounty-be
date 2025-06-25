package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateEarnRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;

public interface RewardPointsTransactionService {
    RewardPointsTransactionResponseDto
        create(Long userId, CreateEarnRewardPointsTransactionRequestDto request);

    List<RewardPointsTransactionResponseDto> getByUserId(Long userId);

    RewardPointsTransactionResponseDto getById(Long id);
}

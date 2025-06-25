package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateEarnRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionDtoResponse;

public interface RewardPointsTransactionService {
    RewardPointsTransactionDtoResponse
        create(Long userId, CreateEarnRewardPointsTransactionRequestDto request);

    List<RewardPointsTransactionDtoResponse> getByUserId(Long userId);

    RewardPointsTransactionDtoResponse getById(Long id);
}

package com.smartbudgetbounty.service.rewardpointstransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateRewardPointsTransactionDtoRequest;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionDtoResponse;

public interface RewardPointsTransactionService {
    RewardPointsTransactionDtoResponse
        create(Long userId, CreateRewardPointsTransactionDtoRequest request);

    List<RewardPointsTransactionDtoResponse> getByUserId(Long userId);

    RewardPointsTransactionDtoResponse getById(Long id);
}

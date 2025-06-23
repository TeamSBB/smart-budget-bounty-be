package com.smartbudgetbounty.service.rewardpointtransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointtransaction.CreateRewardPointTransactionDtoRequest;
import com.smartbudgetbounty.dto.rewardpointtransaction.RewardPointTransactionDtoResponse;

public interface RewardPointTransactionService {
    RewardPointTransactionDtoResponse
        create(Long userId, CreateRewardPointTransactionDtoRequest request);

    List<RewardPointTransactionDtoResponse> getByUserId(Long userId);

    RewardPointTransactionDtoResponse getById(Long id);
}

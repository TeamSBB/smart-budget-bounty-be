package com.smartbudgetbounty.service.rewardpointtransaction;

import java.util.List;

import com.smartbudgetbounty.dto.rewardpointtransaction.CreateRewardPointTransactionDtoRequest;
import com.smartbudgetbounty.dto.rewardpointtransaction.RewardPointTransactionDtoResponse;

public interface RewardPointTransactionService {
    RewardPointTransactionDtoResponse getById(Long id);

    List<RewardPointTransactionDtoResponse> getByUserId(Long userId);

    RewardPointTransactionDtoResponse create(CreateRewardPointTransactionDtoRequest request);
}

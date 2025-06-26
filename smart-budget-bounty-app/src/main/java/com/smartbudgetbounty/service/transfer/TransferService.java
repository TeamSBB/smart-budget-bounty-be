package com.smartbudgetbounty.service.transfer;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.TransferResponseDto;
import com.smartbudgetbounty.entity.Transfer;

public interface TransferService {
    // create and persist Transfer
    // create and persist RewardPointsTransaction
    // - to be called by TransferController
    TransferResponseDto create(CreateTransferDtoRequest request);

    // retrieve Transfer from TransferRepository
    // - to be called by other services
    Transfer getById(Long id);

//    // retrieve a user's RewardPointsTransactions from RewardPointsTransactions and return it as a
//    // RewardPointsTransactionResponseDto
//    // - to be called by RewardPointsTransactionController
//    List<TransferResponseDto> getDtosByUserId(Long userId);
}

package com.smartbudgetbounty.service.transfer;

import java.util.List;

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

    // retrieve Transfer from TransferRepository and return it as a
    // TransferResponseDto
    // - to be called by TransferController
    TransferResponseDto getDtoById(Long id);

    // retrieve a user's Transfers from TransferRepository and return it as a
    // TransferResponseDto
    // - to be called by TransferController
    List<TransferResponseDto> getDtosByUserId(Long userId);
}

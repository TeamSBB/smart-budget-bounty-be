package com.smartbudgetbounty.service.transfer;

import java.util.List;

import com.smartbudgetbounty.dto.transfer.CreateTransferRequestDto;
import com.smartbudgetbounty.dto.transfer.TransferResponseDto;
import com.smartbudgetbounty.entity.Transfer;

public interface TransferService {
    // create a Transfer and associated RewardPointsTransaction
    // persist Transfer, which persists RewardPointsTransaction via cascade
    // - to be called by TransferController
    TransferResponseDto create(Long userId, CreateTransferRequestDto requestDto);

    // retrieve a Transfer from TransferRepository
    // - to be called by other services
    Transfer getById(Long id);

    // retrieve a Transfer from TransferRepository as a TransferResopnseDto
    // - to be called by TransferController
    TransferResponseDto getDtoById(Long id);

    // retrieve a user's list of Transfers from TransferRepository
    // - to be called by other services
    List<Transfer> getByUserId(Long userId);

    // retrieve a user's list of Transfers from TransferRepository as a list of TransferResponseDtos
    // - to be called by TransferController
    List<TransferResponseDto> getDtosByUserId(Long userId);
}

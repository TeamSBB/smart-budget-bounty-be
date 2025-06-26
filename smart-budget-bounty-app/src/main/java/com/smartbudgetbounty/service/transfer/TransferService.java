package com.smartbudgetbounty.service.transfer;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.CreateTransferDtoResponse;
import com.smartbudgetbounty.entity.Transfer;

public interface TransferService {
    // create and persist Transfer
    // - to be called by TransferController
    CreateTransferDtoResponse create(CreateTransferDtoRequest request);

    // retrieve Transfer from TransferRepository
    // - to be called by other services
    Transfer getById(Long id);
}

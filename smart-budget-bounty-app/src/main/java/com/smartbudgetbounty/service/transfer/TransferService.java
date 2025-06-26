package com.smartbudgetbounty.service.transfer;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.CreateTransferDtoResponse;

public interface TransferService {
	CreateTransferDtoResponse create(CreateTransferDtoRequest request);
}

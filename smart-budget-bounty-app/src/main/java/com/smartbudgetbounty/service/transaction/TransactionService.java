package com.smartbudgetbounty.service.transaction;

import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoRequest;
import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoResponse;

public interface TransactionService {
	CreateTransactionDtoResponse create(CreateTransactionDtoRequest request);
}

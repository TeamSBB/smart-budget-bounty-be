package com.smartbudgetbounty.service.transaction;

import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoRequest;
import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoResponse;
import com.smartbudgetbounty.entity.Transaction;

public interface TransactionService {
    // create and persist Transaction
    // - to be called by TransactionController
    CreateTransactionDtoResponse create(CreateTransactionDtoRequest request);

    // retrieve Transaction from TransactionRepository
    // - to be called by other services
    Transaction getById(Long id);
}

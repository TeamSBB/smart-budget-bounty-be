package com.smartbudgetbounty.service.transaction;

import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoRequest;
import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoResponse;
import com.smartbudgetbounty.entity.Transaction;

public interface TransactionService {
    CreateTransactionDtoResponse create(CreateTransactionDtoRequest request);

    Transaction getById(Long id);
}

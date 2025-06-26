package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoRequest;
import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoResponse;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.transaction.TransactionService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transferService;

    public TransactionController(TransactionService transferService) {
        this.transferService = transferService;
    }

    @PostMapping()
    public ResponseEntity<?> createTransaction(
        @Valid @RequestBody
        CreateTransactionDtoRequest createDtoReq
    ) {
        LogUtil.logInfoController(logger, "API called: POST /api/transaction");

        // Call service to insert into db
        CreateTransactionDtoResponse createResponseDto = transferService.create(createDtoReq);

        return ResponseEntity.ok(
            new ApiResponse<>(createResponseDto, "Created transaction successfully.")
        );
    }
}

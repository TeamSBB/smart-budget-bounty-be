package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.CreateTransferDtoResponse;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.transfer.TransferService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }
    
    @PostMapping()
    public ResponseEntity<?> createTransfer(@Valid @RequestBody CreateTransferDtoRequest createDtoReq) {
        LogUtil.logInfoController(logger, "API called: POST /api/transfer");
        
        // Call service to insert into db
        CreateTransferDtoResponse createResponseDto = transferService.create(createDtoReq);
        
        return ResponseEntity.ok(new ApiResponse<>(
    		createResponseDto,
            "Created transaction successfully."
        ));    
    }
}

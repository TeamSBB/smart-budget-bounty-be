package com.smartbudgetbounty.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.TransferResponseDto;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.transfer.TransferService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<ApiResponse<TransferResponseDto>> createTransfer(
        @Valid @RequestBody
        CreateTransferDtoRequest createDtoReq
    ) {
        LogUtil.logInfoController(logger, "API called: POST /api/transfer");

        TransferResponseDto createResponseDto = transferService.create(createDtoReq);

        return ResponseEntity.ok(
            new ApiResponse<TransferResponseDto>(
                createResponseDto,
                "Created Transfer successfully."
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransferResponseDto>> getTransferById(
        @Parameter @PathVariable
        Long id
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/transfer/" + id);

        TransferResponseDto transferResponseDto = transferService.getDtoById(
            id
        );

        return ResponseEntity.ok(
            new ApiResponse<TransferResponseDto>(
                transferResponseDto,
                "Retrieved Transfer successfully."
            )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TransferResponseDto>>> getTransfersByUserId(
        @Parameter @PathVariable
        Long userId
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/transfer/user/" + userId);

        List<TransferResponseDto> transferResponseDtos = transferService.getDtosByUserId(
            userId
        );

        return ResponseEntity.ok(
            new ApiResponse<List<TransferResponseDto>>(
                transferResponseDtos,
                String.format(
                    "Retrieved Transfers for userId %d successfully",
                    userId
                )
            )
        );
    }

}

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
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.transfer.TransferService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Transfer",
    description = "Endpoints for the Transfer entity"
)
@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @Operation(
        summary = "Create a Transfer for a User",
        description = "Creates a new Transfer for the specified User based on the request and returns the transfer details."
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping()
    public ResponseEntity<ApiResponseBody<TransferResponseDto>> createTransfer(
        @Valid @RequestBody
        CreateTransferDtoRequest createDtoReq
    ) {
        LogUtil.logInfoController(logger, "API called: POST /api/transfer");

        TransferResponseDto createResponseDto = transferService.create(createDtoReq);

        return ResponseEntity.ok(
            new ApiResponseBody<TransferResponseDto>(
                createResponseDto,
                "Created Transfer successfully."
            )
        );
    }

    @Operation(
        summary = "Get a Transfer by its ID",
        description = "Retrieves a specific Transfer using its ID and returns the transfer details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody<TransferResponseDto>> getTransferById(
        @Parameter @PathVariable
        Long id
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/transfer/" + id);

        TransferResponseDto transferResponseDto = transferService.getDtoById(
            id
        );

        return ResponseEntity.ok(
            new ApiResponseBody<TransferResponseDto>(
                transferResponseDto,
                "Retrieved Transfer successfully."
            )
        );
    }

    @Operation(
        summary = "Get a User's list of Transfers",
        description = "Retrieves all Transfers associated with the specified User ID and returns the list of transfer details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponseBody<List<TransferResponseDto>>> getTransfersByUserId(
        @Parameter @PathVariable
        Long userId
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/transfer/user/" + userId);

        List<TransferResponseDto> transferResponseDtos = transferService.getDtosByUserId(
            userId
        );

        return ResponseEntity.ok(
            new ApiResponseBody<List<TransferResponseDto>>(
                transferResponseDtos,
                String.format(
                    "Retrieved Transfers for userId %d successfully",
                    userId
                )
            )
        );
    }

}

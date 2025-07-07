package com.smartbudgetbounty.controller;

import com.smartbudgetbounty.dto.transfer.CreateTransferRequestDto;
import com.smartbudgetbounty.dto.transfer.SearchTransferRequestDto;
import com.smartbudgetbounty.dto.transfer.SearchTransferResponseDto;
import com.smartbudgetbounty.dto.transfer.TransferResponseDto;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.transfer.TransferService;
import com.smartbudgetbounty.util.LogUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponseBody<TransferResponseDto>> createTransfer(
            @Parameter(description = "ID of the User", required = true) @PathVariable
            Long userId,
            @Valid @RequestBody
            CreateTransferRequestDto createDtoReq
    ) {
        LogUtil.logInfoController(logger, "API called: POST /api/transfer/user/" + userId);

        TransferResponseDto transferResponseDto = transferService.create(userId, createDtoReq);

        URI location = URI.create("/api/transfer/user/" + userId);

        return ResponseEntity.created(location).body(
                new ApiResponseBody<TransferResponseDto>(
                        transferResponseDto,
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
            @Parameter(description = "ID of the Transfer", required = true) @PathVariable
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
            @Parameter(description = "ID of the User", required = true) @PathVariable
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

    @PostMapping("/search/{userId}")
    public ResponseEntity<ApiResponseBody<SearchTransferResponseDto>> searchTransfersByUserId(
            @PathVariable Long userId,
            @RequestBody SearchTransferRequestDto requestDto) {
        LogUtil.logInfoController(logger, "API called: GET /api/transfer/search/" + userId);

        SearchTransferResponseDto transferResponseDtos = transferService.searchTransfersByUserId(userId, requestDto);

        return ResponseEntity.ok(
                new ApiResponseBody<SearchTransferResponseDto>(
                        transferResponseDtos,
                        String.format(
                                "Retrieved Transfers for userId %d successfully",
                                userId
                        )
                )
        );
    }
}

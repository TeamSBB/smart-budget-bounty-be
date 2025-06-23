package com.smartbudgetbounty.controller;

import java.net.URI;
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

import com.smartbudgetbounty.dto.rewardpointtransaction.CreateRewardPointTransactionDtoRequest;
import com.smartbudgetbounty.dto.rewardpointtransaction.RewardPointTransactionDtoResponse;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.rewardpointtransaction.RewardPointTransactionService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Reward Point Transactions",
    description = "Operations related to reward point transactions"
)
@RestController
@RequestMapping("/api/reward-point-transaction")
public class RewardPointTransactionController {
    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointTransactionController.class
    );

    private final RewardPointTransactionService rewardPointTransactionService;

    public RewardPointTransactionController(
        RewardPointTransactionService rewardPointTransactionService
    ) {
        this.rewardPointTransactionService = rewardPointTransactionService;
    }

    @Operation(summary = "Create a reward point transaction")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "201",
        description = "Created"
    )
    @PostMapping("/user/{userId}")
    public
        ResponseEntity<ApiResponse<RewardPointTransactionDtoResponse>>
        createRewardPointTransaction(
            @Parameter(description = "ID of the user", required = true) @PathVariable
            Long userId,
            @Valid @RequestBody
            CreateRewardPointTransactionDtoRequest createDtoReq
        ) {
        LogUtil.logInfoController(logger, "API called: POST /api/reward-point-transaction");

        // Call service to insert into db
        RewardPointTransactionDtoResponse rewardPointTransactionDto = rewardPointTransactionService.create(
            userId,
            createDtoReq
        );

        URI location = URI.create(
            "/api/reward-point-transaction/" + rewardPointTransactionDto.getId()
        );

        return ResponseEntity.created(location).body(
            new ApiResponse<RewardPointTransactionDtoResponse>(
                rewardPointTransactionDto,
                "Created RewardPointTransaction successfully."
            )
        );

    }

    @Operation(summary = "Retrieve a user's list of reward point transactions")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/user/{userId}")
    public
        ResponseEntity<ApiResponse<List<RewardPointTransactionDtoResponse>>>
        getRewardPointTransactionsByUserId(
            @Parameter(description = "ID of the user", required = true) @PathVariable
            Long userId
        ) {
        LogUtil.logInfoController(
            logger,
            "API called: GET /api/reward-point-transaction/user/" + userId
        );

        List<RewardPointTransactionDtoResponse> rewardPointTransactionDtos = rewardPointTransactionService.getByUserId(
            userId
        );

        return ResponseEntity.ok(
            new ApiResponse<List<RewardPointTransactionDtoResponse>>(
                rewardPointTransactionDtos,
                String.format(
                    "Retrieved RewardPointTransactions for userId %d successfully",
                    userId
                )
            )
        );
    }

    @Operation(summary = "Retrieve a reward point transaction by its ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public
        ResponseEntity<ApiResponse<RewardPointTransactionDtoResponse>>
        getRewardPointTransactionById(
            @Parameter(description = "ID of the reward point transaction", required = true)
            @PathVariable
            Long id
        ) {
        LogUtil.logInfoController(logger, "API called: GET /api/reward-point-transaction/" + id);

        RewardPointTransactionDtoResponse responseDto = rewardPointTransactionService.getById(id);

        return ResponseEntity.ok(
            new ApiResponse<RewardPointTransactionDtoResponse>(
                responseDto,
                "Retrieved RewardPointTransaction successfully."
            )
        );
    }
}

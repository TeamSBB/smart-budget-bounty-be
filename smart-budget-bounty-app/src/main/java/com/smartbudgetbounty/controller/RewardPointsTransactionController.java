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

import com.smartbudgetbounty.dto.rewardpointstransaction.CreateRedeemRewardPointsTransactionRequestDto;
import com.smartbudgetbounty.dto.rewardpointstransaction.RewardPointsTransactionResponseDto;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Reward Points Transactions",
    description = "Operations related to reward points transactions"
)
@RestController
@RequestMapping("/api/reward-point-transaction")
public class RewardPointsTransactionController {
    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionController.class
    );

    private final RewardPointsTransactionService rewardPointsTransactionService;

    public RewardPointsTransactionController(
        RewardPointsTransactionService rewardPointsTransactionService
    ) {
        this.rewardPointsTransactionService = rewardPointsTransactionService;
    }

    @Operation(
        summary = "Create a REDEEM RewardPointsTransaction"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "201",
        description = "Created"
    )
    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<RewardPointsTransactionResponseDto>> createRedeemRewardPointsTransaction(
        @Parameter(description = "ID of the user", required = true) @PathVariable
        Long userId,
        @Valid @RequestBody
        CreateRedeemRewardPointsTransactionRequestDto requestDto
    ) {
        LogUtil.logInfoController(logger, "API called: POST /api/reward-point-transaction");

        // create and persist RewardPointsTransaction
        RewardPointsTransactionResponseDto rewardPointsTransactionDto = rewardPointsTransactionService.createRedeem(
            userId,
            requestDto
        );

        // response
        URI location = URI.create(
            "/api/reward-point-transaction/" + rewardPointsTransactionDto.getId()
        );

        return ResponseEntity.created(location).body(
            new ApiResponse<RewardPointsTransactionResponseDto>(
                rewardPointsTransactionDto,
                "Created RewardPointsTransaction successfully."
            )
        );

    }

    @Operation(
        summary = "Retrieve a RewardPointsTransaction by its ID"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RewardPointsTransactionResponseDto>> getRewardPointsTransactionById(
        @Parameter(
            description = "ID of the reward points transaction",
            required = true
        ) @PathVariable
        Long id
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/reward-point-transaction/" + id);

        RewardPointsTransactionResponseDto responseDto = rewardPointsTransactionService.getDtoById(
            id
        );

        return ResponseEntity.ok(
            new ApiResponse<RewardPointsTransactionResponseDto>(
                responseDto,
                "Retrieved RewardPointsTransaction successfully."
            )
        );
    }

    @Operation(
        summary = "Retrieve a user's list of RewardPointsTransactions"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "200",
        description = "OK"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<RewardPointsTransactionResponseDto>>> getRewardPointsTransactionsByUserId(
        @Parameter(description = "ID of the user", required = true) @PathVariable
        Long userId
    ) {
        LogUtil.logInfoController(
            logger,
            "API called: GET /api/reward-point-transaction/user/" + userId
        );

        List<RewardPointsTransactionResponseDto> rewardPointsTransactionDtos = rewardPointsTransactionService.getDtosByUserId(
            userId
        );

        return ResponseEntity.ok(
            new ApiResponse<List<RewardPointsTransactionResponseDto>>(
                rewardPointsTransactionDtos,
                String.format(
                    "Retrieved RewardPointsTransactions for userId %d successfully",
                    userId
                )
            )
        );
    }
}

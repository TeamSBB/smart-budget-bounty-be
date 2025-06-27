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
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Reward Points Transaction",
    description = "Endpoints for the RewardPointsTransaction entity, including redemption and retrieval operations"
)
@RestController
@RequestMapping("/api/reward-points-transaction")
public class RewardPointsTransactionController {
    private static final Logger logger = LoggerFactory.getLogger(
        RewardPointsTransactionController.class
    );

    private final RewardPointsTransactionService pointsTransactionService;

    public RewardPointsTransactionController(
        RewardPointsTransactionService pointsTransactionService
    ) {
        this.pointsTransactionService = pointsTransactionService;
    }

    @Operation(
        summary = "Redeem reward points for a User",
        description = "Creates a new RewardPointsTransaction to redeem reward points for the specified User in exchange for a RewardVoucher, based on the request. Returns the transaction details."
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponseBody<RewardPointsTransactionResponseDto>> createRedeemPointsTransaction(
        @Parameter(description = "ID of the User", required = true) @PathVariable
        Long userId,
        @Valid @RequestBody
        CreateRedeemRewardPointsTransactionRequestDto requestDto
    ) {
        LogUtil.logInfoController(logger, "API called: POST /api/reward-points-transaction");

        RewardPointsTransactionResponseDto pointsTransactionResponseDto = pointsTransactionService.createRedeem(
            userId,
            requestDto
        );

        URI location = URI.create(
            "/api/reward-points-transaction/" + pointsTransactionResponseDto.getId()
        );

        return ResponseEntity.created(location).body(
            new ApiResponseBody<RewardPointsTransactionResponseDto>(
                pointsTransactionResponseDto,
                "Created RewardPointsTransaction successfully."
            )
        );
    }

    @Operation(
        summary = "Get a RewardPointsTransaction by its ID",
        description = "Retrieves a specific RewardPointsTransaction using its ID and returns the transaction details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody<RewardPointsTransactionResponseDto>> getPointsTransactionById(
        @Parameter(description = "ID of the RewardPointsTransaction", required = true) @PathVariable
        Long id
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/reward-points-transaction/" + id);

        RewardPointsTransactionResponseDto pointsTransactionResponseDto = pointsTransactionService.getDtoById(
            id
        );

        return ResponseEntity.ok(
            new ApiResponseBody<RewardPointsTransactionResponseDto>(
                pointsTransactionResponseDto,
                "Retrieved RewardPointsTransaction successfully."
            )
        );
    }

    @Operation(
        summary = "Get a User's list of RewardPointsTransactions",
        description = "Retrieves all RewardPointsTransactions associated with the specified User ID and returns the list of transaction details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponseBody<List<RewardPointsTransactionResponseDto>>> getPointsTransactionsByUserId(
        @Parameter(description = "ID of the User", required = true) @PathVariable
        Long userId
    ) {
        LogUtil.logInfoController(
            logger,
            "API called: GET /api/reward-points-transaction/user/" + userId
        );

        List<RewardPointsTransactionResponseDto> pointsTransactionResponseDtos = pointsTransactionService.getDtosByUserId(
            userId
        );

        return ResponseEntity.ok(
            new ApiResponseBody<List<RewardPointsTransactionResponseDto>>(
                pointsTransactionResponseDtos,
                String.format(
                    "Retrieved RewardPointsTransactions for userId %d successfully",
                    userId
                )
            )
        );
    }
}

package com.smartbudgetbounty.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.rewardvoucher.RedeemRewardVoucherRequestDto;
import com.smartbudgetbounty.dto.rewardvoucher.RewardVoucherResponseDto;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.rewardvoucher.RewardVoucherService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Reward Voucher",
    description = "Endpoints for the RewardVoucher entity, including retrieval and redemption operations"
)
@RestController
@RequestMapping("/api/reward-voucher")
public class RewardVoucherController {
    private static final Logger logger = LoggerFactory.getLogger(RewardVoucherController.class);

    private final RewardVoucherService voucherService;

    public RewardVoucherController(RewardVoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @Operation(
        summary = "Get a RewardVoucher by its ID",
        description = "Retrieves a specific RewardVoucher using its ID and returns the voucher details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody<RewardVoucherResponseDto>> getVoucherById(
        @Parameter(description = "ID of the RewardVoucher", required = true) @PathVariable
        Long id
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/reward-voucher/" + id);

        RewardVoucherResponseDto voucherResponseDto = voucherService.getDtoById(id);

        return ResponseEntity.ok(
            new ApiResponseBody<RewardVoucherResponseDto>(
                voucherResponseDto,
                "Retrieved RewardVoucher successfully."
            )
        );
    }

    @Operation(
        summary = "Get a User's list of RewardVouchers",
        description = "Retrieves all RewardVouchers associated with the specified User ID and returns the list of voucher details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponseBody<List<RewardVoucherResponseDto>>> getVouchersByUserId(
        @Parameter(description = "ID of the User", required = true) @PathVariable
        Long userId
    ) {
        LogUtil.logInfoController(
            logger,
            "API called: GET /api/reward-voucher/user/" + userId
        );

        List<RewardVoucherResponseDto> voucherResponseDtos = voucherService.getDtosByUserId(userId);

        return ResponseEntity.ok(
            new ApiResponseBody<List<RewardVoucherResponseDto>>(
                voucherResponseDtos,
                String.format(
                    "Retrieved RewardVouchers for userId %d successfully",
                    userId
                )
            )
        );
    }

    @Operation(
        summary = "Redeem a RewardVoucher by its ID",
        description = "Marks the specified RewardVoucher as redeemed and returns the updated voucher details."
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(
        responseCode = "400",
        description = "Bad Request: the user does not own the RewardVoucher or the RewardVoucher is not available"
    )
    @PutMapping("/{id}/redeem")
    public ResponseEntity<ApiResponseBody<RewardVoucherResponseDto>> redeemVoucher(
        @Parameter(description = "ID of the RewardVoucher", required = true) @PathVariable
        Long id,
        @Valid @RequestBody
        RedeemRewardVoucherRequestDto requestDto
    ) {
        LogUtil.logInfoController(
            logger,
            String.format("API called: PUT /api/reward-voucher/%d/redeem", id)
        );

        RewardVoucherResponseDto voucherResponseDto = voucherService.redeem(id, requestDto);

        return ResponseEntity.ok(
            new ApiResponseBody<RewardVoucherResponseDto>(
                voucherResponseDto,
                "Redeemed RewardVoucher successfully"
            )
        );
    }
}

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
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.service.rewardvoucher.RewardVoucherService;
import com.smartbudgetbounty.util.LogUtil;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reward-voucher")
public class RewardVoucherController {
    private static final Logger logger = LoggerFactory.getLogger(RewardVoucherController.class);

    private final RewardVoucherService voucherService;

    public RewardVoucherController(RewardVoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RewardVoucherResponseDto>> getVoucherById(
        @Parameter(description = "ID of the RewardVoucher", required = true) @PathVariable
        Long id
    ) {
        LogUtil.logInfoController(logger, "API called: GET /api/reward-voucher/" + id);

        RewardVoucherResponseDto voucherResponseDto = voucherService.getDtoById(id);

        return ResponseEntity.ok(
            new ApiResponse<RewardVoucherResponseDto>(
                voucherResponseDto,
                "Retrieved RewardVoucher successfully."
            )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<RewardVoucherResponseDto>>> getVouchersByUserId(
        @Parameter(description = "ID of the User", required = true) @PathVariable
        Long userId
    ) {
        LogUtil.logInfoController(
            logger,
            "API called: GET /api/reward-voucher/user/" + userId
        );

        List<RewardVoucherResponseDto> voucherResponseDtos = voucherService.getDtosByUserId(userId);

        return ResponseEntity.ok(
            new ApiResponse<List<RewardVoucherResponseDto>>(
                voucherResponseDtos,
                String.format(
                    "Retrieved RewardVouchers for userId %d successfully",
                    userId
                )
            )
        );
    }

    @PutMapping("/{voucherId}/redeem")
    public ResponseEntity<ApiResponse<RewardVoucherResponseDto>> redeemVoucher(
        @Parameter(description = "ID of the RewardVoucher", required = true) @PathVariable
        Long voucherId,
        @Valid @RequestBody
        RedeemRewardVoucherRequestDto requestDto
    ) {
        LogUtil.logInfoController(
            logger,
            String.format("API called: PUT /api/reward-voucher/%d/redeem", voucherId)
        );

        RewardVoucherResponseDto voucherResponseDto = voucherService.redeem(voucherId, null);

        return ResponseEntity.ok(
            new ApiResponse<RewardVoucherResponseDto>(
                voucherResponseDto,
                "Redeemed RewardVoucher successfully"
            )
        );
    }
}

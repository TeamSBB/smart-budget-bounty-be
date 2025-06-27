package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.profile.GetProfileInfoDtoResponse;
import com.smartbudgetbounty.dto.profile.UpsertProfileBankInfoDtoRequest;
import com.smartbudgetbounty.dto.profile.UpsertProfileBankInfoDtoResponse;
import com.smartbudgetbounty.dto.profile.UpsertProfileCardInfoDtoRequest;
import com.smartbudgetbounty.dto.profile.UpsertProfileCardInfoDtoResponse;
import com.smartbudgetbounty.dto.profile.UpsertProfilePaynowInfoDtoRequest;
import com.smartbudgetbounty.dto.profile.UpsertProfilePaynowInfoDtoResponse;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.profile.ProfileService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@GetMapping("{accountId}")
    public ResponseEntity<?> getProfileInfo(@PathVariable Long accountId) {
        LogUtil.logInfoController(logger, "API called: GET /api/profile");
        
        // Call service to insert into db
        GetProfileInfoDtoResponse getResponseDto = profileService.getProfileInfo(accountId);
        
        return ResponseEntity.ok(new ApiResponseBody<>(
    		getResponseDto,
            "Get Profile Info successfully."
        ));    
    }

    @PostMapping("/bank")
    public ResponseEntity<?> upsertProfileBankInfo(@Valid @RequestBody UpsertProfileBankInfoDtoRequest upsertDtoReq) {
        LogUtil.logInfoController(logger, "API called: POST /api/profile/bank");
        
        // Call service to insert into db
        UpsertProfileBankInfoDtoResponse upsertResponseDto = profileService.upsertBankInfo(upsertDtoReq);
        
        return ResponseEntity.ok(new ApiResponseBody<>(
    		upsertResponseDto,
            "Upsert Profile Bank Info successfully."
        ));    
    }

    @PostMapping("/card")
    public ResponseEntity<?> upsertProfileCardInfo(@Valid @RequestBody UpsertProfileCardInfoDtoRequest upsertDtoReq) {
        LogUtil.logInfoController(logger, "API called: POST /api/profile/card");
        
        // Call service to insert into db
        UpsertProfileCardInfoDtoResponse upsertResponseDto = profileService.upsertCardInfo(upsertDtoReq);
        
        return ResponseEntity.ok(new ApiResponseBody<>(
    		upsertResponseDto,
            "Upsert Profile Card Info successfully."
        ));    
    }

    @PostMapping("/paynow")
    public ResponseEntity<?> upsertProfilePaynowInfo(@Valid @RequestBody UpsertProfilePaynowInfoDtoRequest upsertDtoReq) {
        LogUtil.logInfoController(logger, "API called: POST /api/profile/paynow");
        
        // Call service to insert into db
        UpsertProfilePaynowInfoDtoResponse upsertResponseDto = profileService.upsertPaynowInfo(upsertDtoReq);
        
        return ResponseEntity.ok(new ApiResponseBody<>(
    		upsertResponseDto,
            "Upsert Profile paynow Info successfully."
        ));    
    }
}

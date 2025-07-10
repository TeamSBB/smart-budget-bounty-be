package com.smartbudgetbounty.controller;

import com.smartbudgetbounty.dto.auth.LoginDtoRequest;
import com.smartbudgetbounty.dto.auth.LoginDtoResponse;
import com.smartbudgetbounty.dto.auth.RegisterDtoRequest;
import com.smartbudgetbounty.dto.auth.RegisterDtoResponse;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Auth",
        description = "Endpoints for the Auth entity"
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Sandbox1Controller.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDtoRequest loginUserDtoRequest) {
        LogUtil.logInfoController(logger, "API called: POST /api/auth/login");

        LoginDtoResponse loginResponseDto = userService.loginUser(loginUserDtoRequest);

        return ResponseEntity.ok(new ApiResponseBody<>(
                loginResponseDto,
                "Token generated for login."
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDtoRequest registerUserDto) {
        LogUtil.logInfoController(logger, "API called: POST /api/auth/register");

        // Format DTO and return response.
        RegisterDtoResponse registerResponseDto = userService.registerUser(registerUserDto);

        return ResponseEntity.ok(new ApiResponseBody<>(
                registerResponseDto,
                "Created user successfully."
        ));
    }

    // Note
    // 1. No logout, as JWT is stateless.
    // 2. Future enhancement, refresh Token.
}
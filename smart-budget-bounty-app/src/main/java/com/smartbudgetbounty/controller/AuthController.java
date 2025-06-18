package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.auth.LoginDtoRequest;
import com.smartbudgetbounty.dto.auth.LoginDtoResponse;
import com.smartbudgetbounty.dto.auth.RegisterDtoRequest;
import com.smartbudgetbounty.dto.auth.RegisterDtoResponse;
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.service.jwt.JwtService;
import com.smartbudgetbounty.util.LogUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Sandbox1Controller.class);
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDtoRequest loginUserDtoRequest) {
        LogUtil.logInfoController(logger, "API called: GET /api/auth/login");
        
        // Check email and password
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
        		loginUserDtoRequest.getEmail(),
        		loginUserDtoRequest.getPassword()
            )
        );
        
        // If success, generate jwt token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();        
        String token = jwtService.generateToken(userDetails.getUsername());
        

        // Format DTO and return response.
        LoginDtoResponse loginResponseDto = new LoginDtoResponse(token);
        
        return ResponseEntity.ok(new ApiResponse<>(
    		loginResponseDto,
            "Token generated for login."
        ));    
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDtoRequest registerUserDto) {
        LogUtil.logInfoController(logger, "API called: GET /api/auth/register");
        
        // User already exist, cannot register user.
        if (userRepository.existsByUsername(registerUserDto.getUsername())) {
             LogUtil.logErrorController(logger, "Email is already taken.");

             return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(
                 null,
                 "Email is already taken."
             ));
        }
        
        // User don't exist, register user.
        User newUser = new User(
                null,
                registerUserDto.getUsername(),
                encoder.encode(registerUserDto.getPassword()),
        		registerUserDto.getAddress(),
        		registerUserDto.getContactNumber(),
        		registerUserDto.getEmail(),
        		registerUserDto.getFirstName(),
        		registerUserDto.getLastName()
        );
        userRepository.save(newUser);
        
        // Format DTO and return response.
        RegisterDtoResponse registerResponseDto = new RegisterDtoResponse(newUser.getUsername());
        
        return ResponseEntity.ok(new ApiResponse<>(
    		registerResponseDto,
            "Created user successfully."
        ));
    }
    
    // Note
    // 1. No logout, as JWT is stateless.
    // 2. Future enhancement, refresh Token.
}
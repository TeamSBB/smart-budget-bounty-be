package com.smartbudgetbounty.config;
 
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType; // For setting Content-Type
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper; // Import ObjectMapper
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.exception.GlobalExceptionHandler;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
 
    // Inject ObjectMapper (Spring will automatically provide it)
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
    public AuthEntryPointJwt(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException, ServletException {
        LogUtil.logErrorGlobal(logger, "An unexpected error occurred: {} at path: {}", authException.getMessage(), request.getRequestURI());
 
        // Set the response status and content type
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Set Content-Type to application/json
 
        // Write the JSON response body
        objectMapper.writeValue(response.getOutputStream(), new ApiResponse<>(
            null,
            authException.getMessage() != null ? authException.getMessage() : "Unauthorized access"            
        ));
    }
}
 
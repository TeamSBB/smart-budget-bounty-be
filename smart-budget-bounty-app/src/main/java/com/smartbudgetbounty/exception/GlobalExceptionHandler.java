package com.smartbudgetbounty.exception;

import java.io.IOException; // Needed for direct response writing
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper; // Import ObjectMapper
import com.smartbudgetbounty.entity.ApiResponse;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.servlet.http.HttpServletResponse; // Needed for direct response writing
 
@RestControllerAdvice
public class GlobalExceptionHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
    private final ObjectMapper objectMapper; // Inject ObjectMapper
 
    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
 
    // --- Example: Handling MethodArgumentNotValidException (Validation Errors) ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
 
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
 
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        LogUtil.logErrorGlobal(logger, "Validation failed: {}: path: {}", errors, path);
 
        ApiResponse<Void> apiError = new ApiResponse<>(
                null,
                ex.getMessage() != null ? ex.getMessage() : "Validation failed"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
 
     @ExceptionHandler(NotFoundException.class) // Assuming NotFoundException is a custom exception
     public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException ex, WebRequest request) {
    	 String path = ((ServletWebRequest)request).getRequest().getRequestURI();
         LogUtil.logErrorGlobal(logger, "Entity not found: {}, path: {}", ex.getMessage(), path);
         
         ApiResponse<Void> apiError = new ApiResponse<>(
                 null,
                 ex.getMessage() != null ? ex.getMessage() : "Resource not found"
         );
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
     }

     
     @ExceptionHandler(BadCredentialsException.class) // Assuming NotFoundException is a custom exception
     public ResponseEntity<ApiResponse<Void>> handleUnauthorziedException(BadCredentialsException ex, WebRequest request) {

    	 String path = ((ServletWebRequest)request).getRequest().getRequestURI();
         LogUtil.logErrorGlobal(logger, "Bad Credentials error: {}, path: {}", ex.getMessage(), path);
         
         ApiResponse<Void> apiError = new ApiResponse<>(
                 null,
             ex.getMessage() != null ? ex.getMessage() : "BadCredentialsException error."
         );
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
     }
 
    // --- Global Catch-All for other unexpected exceptions ---
    @ExceptionHandler(Exception.class)
    public void handleAllOtherExceptions(
            Exception ex, HttpServletResponse response, WebRequest request) throws IOException {
 
    	String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        LogUtil.logErrorGlobal(logger, "An unexpected error occurred: {}, path: {}", ex.getMessage(), path);
  
        // Create your custom error response object for internal server error
        ApiResponse<Void> apiError = new ApiResponse<>(
            null,
            "An unexpected error occurred. Please try again later."
        );
 
        // Set the response status and content type
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
 
        // Write the JSON response body
        objectMapper.writeValue(response.getOutputStream(), apiError);
    }
}
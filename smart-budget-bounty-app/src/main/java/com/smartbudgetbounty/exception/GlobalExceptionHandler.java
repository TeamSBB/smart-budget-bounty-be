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
import com.smartbudgetbounty.entity.ApiResponseError;
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
    public ResponseEntity<ApiResponseError> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
 
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
 
        LogUtil.logErrorGlobal(logger, "Validation failed: {}", errors);
 
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
 
        ApiResponseError apiError = new ApiResponseError(
            HttpStatus.BAD_REQUEST,
            "Validation failed", // A general message for validation errors
            path
        );
        apiError.setError(errors.toString()); // You can choose to put specific field errors here
 
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
 
     @ExceptionHandler(NotFoundException.class) // Assuming NotFoundException is a custom exception
     public ResponseEntity<ApiResponseError> handleNotFoundException(NotFoundException ex, WebRequest request) {

         LogUtil.logErrorGlobal(logger, "Entity not found: {}", ex.getMessage());
         
    	 String path = ((ServletWebRequest)request).getRequest().getRequestURI();
         ApiResponseError apiError = new ApiResponseError(
             HttpStatus.NOT_FOUND,
             ex.getMessage() != null ? ex.getMessage() : "Resource not found",
             path
         );
         return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
     }

     
     @ExceptionHandler(BadCredentialsException.class) // Assuming NotFoundException is a custom exception
     public ResponseEntity<ApiResponseError> handleUnauthorziedException(BadCredentialsException ex, WebRequest request) {

         LogUtil.logErrorGlobal(logger, "Bad Credentials error: {}", ex.getMessage());
         
    	 String path = ((ServletWebRequest)request).getRequest().getRequestURI();
         ApiResponseError apiError = new ApiResponseError(
             HttpStatus.UNAUTHORIZED,
             ex.getMessage() != null ? ex.getMessage() : "BadCredentialsException error.",
             path
         );
         return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
     }
 
    // --- Global Catch-All for other unexpected exceptions ---
    @ExceptionHandler(Exception.class)
    public void handleAllOtherExceptions(
            Exception ex, HttpServletResponse response, WebRequest request) throws IOException {
 
        LogUtil.logErrorGlobal(logger, "An unexpected error occurred: {}", ex.getMessage());
 
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
 
        // Create your custom error response object for internal server error
        ApiResponseError apiError = new ApiResponseError(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An unexpected error occurred. Please try again later.", // Generic message for clients
            path
        );
 
        // Set the response status and content type
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
 
        // Write the JSON response body
        objectMapper.writeValue(response.getOutputStream(), apiError);
    }
}

//@RestControllerAdvice
//public class GlobalExceptionHandler {
// 
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
// 
//    // Handle validation errors (like @Valid failures)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//        
//        LogUtil.logWarnGlobal(logger, "Validation failed: {}", errors);
//        
//        return ResponseEntity.badRequest().body(errors);
//    }
// 
//    // Handle entity not found (e.g., your custom or JPA exception)
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
//        LogUtil.logWarnGlobal(logger, "Entity not found: {}", ex.getMessage());
//        
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
// 
//    // Catch all other exceptions (generic 500)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleAllExceptions(Exception ex) {
//        LogUtil.logErrorGlobal(logger, "Unexpected error occurred", ex);
//        
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
//    }
//}
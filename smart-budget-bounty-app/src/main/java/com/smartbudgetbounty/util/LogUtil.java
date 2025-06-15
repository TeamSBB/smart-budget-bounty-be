package com.smartbudgetbounty.util;

import org.slf4j.Logger;
import org.slf4j.MDC;

public class LogUtil {
    // Get requestId from MDC or fallback
    public static String getRequestId() {
        String id = MDC.get("X-Request-Id");
        return id != null ? id : "N/A";
    }
 
    // Get userId from MDC or fallback
    public static String getUserId() {
        String id = MDC.get("User-Id");
        return id != null ? id : "N/A";
    }
    
    // For Global Exception - High level Log
    public static void logInfoGlobal(Logger logger, String message, Object... args) {
        String requestId = getRequestId();
        String userId = getUserId();
 
        logger.info("[requestId={}, userId={}] Global Exception - [INFO] - " + message, getRequestId(), getUserId(), args);
    }
    
    // For Global Exception - High level Log
    public static void logErrorGlobal(Logger logger, String message, Object... args) {
        String requestId = getRequestId();
        String userId = getUserId();
 
        logger.error("[requestId={}, userId={}] Global Exception - [ERROR] - " + message, getRequestId(), getUserId(), args);
    }
    
    // For Global Exception - High level Log
    public static void logWarnGlobal(Logger logger, String message, Object... args) {
        String requestId = getRequestId();
        String userId = getUserId();
 
        logger.warn("[requestId={}, userId={}] Global Exception - [WARN] - " + message, getRequestId(), getUserId(), args);
    }
    
    // For Controller - High level Log
    public static void logInfoController(Logger logger, String message, Object... args) {
        String requestId = getRequestId();
        String userId = getUserId();
 
        logger.info("[requestId={}, userId={}] Controller - [INFO] - " + message, getRequestId(), getUserId(), args);
    }
    
    // Log start message with context
    public static void logStart(Logger logger, String message, Object... args) {
    	logger.info("[requestId={}, userId={}] Service - START - " + message, getRequestId(), getUserId(), args);
    }
 
    // Log end message with context
    public static void logEnd(Logger logger, String message, Object... args) {
    	logger.info("[requestId={}, userId={}] Service - END - " + message, getRequestId(), getUserId(), args);
    }
 
    // Log error message with context
    public static void logError(Logger logger, String message, Object... args) {
        logger.error("[requestId={}, userId={}] Service - [ERROR] - " + message, getRequestId(), getUserId(), args);
    }
 
    // Log Warning message with context
    public static void logWarn(Logger logger, String message, Object... args) {
        logger.warn("[requestId={}, userId={}] Service - [WARN] - " + message, getRequestId(), getUserId(), args);
    }
}

package com.smartbudgetbounty.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smartbudgetbounty.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class LoggingFilter extends OncePerRequestFilter {
 
    public static final String REQUEST_ID_HEADER = "X-Request-Id";
    public static final String USER_ID_MDC_KEY = "userId";
    public static final String REQUEST_ID_MDC_KEY = "requestId";
 
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestId = request.getHeader(REQUEST_ID_HEADER);
            if (requestId == null || requestId.isEmpty()) {
                requestId = UUID.randomUUID().toString();
            }
            MDC.put(REQUEST_ID_MDC_KEY, requestId);
 
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = "0l";
        	System.out.println(authentication); // this return me annoymous
            if (authentication != null && authentication.isAuthenticated()) {
            	// If you have a custom UserDetails with an ID, you might do:
            	//TODO
                 if (authentication.getPrincipal() instanceof User) {
                     userId = ((User) authentication.getPrincipal()).getId().toString();
                 }
            }
            MDC.put(USER_ID_MDC_KEY, userId);
 
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(REQUEST_ID_MDC_KEY);
            MDC.remove(USER_ID_MDC_KEY);
        }
    }
}
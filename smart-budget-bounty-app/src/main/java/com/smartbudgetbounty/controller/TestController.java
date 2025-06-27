package com.smartbudgetbounty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.entity.ApiResponseBody;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public ResponseEntity<?> allAccess() { 
        return ResponseEntity.ok(new ApiResponseBody<Void>(
    		null,
            "Public Content."
        ));
    }
    
    @GetMapping("/user")
    public ResponseEntity<?> userAccess() {
        return ResponseEntity.ok(new ApiResponseBody<Void>(
    		null,
            "User Content."
        ));
    }
}
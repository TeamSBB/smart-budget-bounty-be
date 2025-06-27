package com.smartbudgetbounty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbudgetbounty.dto.sandbox1.Sandbox1DtoRequest;
import com.smartbudgetbounty.dto.sandbox1.Sandbox1DtoResponse;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.sandbox1.Sandbox1Service;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/sandbox1")
public class Sandbox1Controller {
 
    private static final Logger logger = LoggerFactory.getLogger(Sandbox1Controller.class);
    private final Sandbox1Service sandbox1Service;
 
    public Sandbox1Controller(Sandbox1Service sandbox1Service) {
        this.sandbox1Service = sandbox1Service;
    }
 
    @GetMapping
    public ResponseEntity<?> getAll() {
        LogUtil.logInfoController(logger, "API called: GET /api/sandbox1");
       
        return ResponseEntity.ok(new ApiResponseBody<>(
    		sandbox1Service.getAll(),
            "getAll successfully."
        ));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        LogUtil.logInfoController(logger, "API called: GET /api/sandbox1/{}", id);

        return ResponseEntity.ok(new ApiResponseBody<>(
    		sandbox1Service.getById(id),
            "getById successfully."
        ));
    }
 
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Sandbox1DtoRequest request) {
        LogUtil.logInfoController(logger, "API called: POST /api/sandbox1");

        Sandbox1DtoResponse dtoResponse = sandbox1Service.create(request);
        return ResponseEntity.ok(new ApiResponseBody<>(
    		dtoResponse,
            "create successfully."
        ));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Sandbox1DtoRequest request) {
        LogUtil.logInfoController(logger, "API called: PUT /api/sandbox1/{}", id);
        Sandbox1DtoResponse response = sandbox1Service.updateById(id, request);
        return ResponseEntity.ok(new ApiResponseBody<>(
    		response,
            "updateById successfully."
        ));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        LogUtil.logInfoController(logger, "API called: DELETE /api/sandbox1/{}", id);
        sandbox1Service.deleteById(id);
        
        return ResponseEntity.ok(new ApiResponseBody<>(
    		null,
            "deleteById successfully."
        ));
    }
}
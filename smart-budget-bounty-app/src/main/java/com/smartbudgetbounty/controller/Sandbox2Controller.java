package com.smartbudgetbounty.controller;

import java.util.List;

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

import com.smartbudgetbounty.dto.sandbox2.Sandbox2PersonDtoRequest;
import com.smartbudgetbounty.dto.sandbox2.Sandbox2PersonDtoResponse;
import com.smartbudgetbounty.entity.ApiResponseBody;
import com.smartbudgetbounty.service.sandbox2.Sandbox2PersonService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/sandbox2")
public class Sandbox2Controller {
 
    private static final Logger logger = LoggerFactory.getLogger(Sandbox2Controller.class);
 
    private final Sandbox2PersonService service;
 
    public Sandbox2Controller(Sandbox2PersonService service) {
        this.service = service;
    }
 
    @GetMapping
    public ResponseEntity<?> getAll() {
        LogUtil.logInfoController(logger, "API called: GET /api/sandbox2");
        List<Sandbox2PersonDtoResponse> result = service.getAll();

        return ResponseEntity.ok(new ApiResponseBody<>(
    		result,
            "getAll successfully."
        ));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        LogUtil.logInfoController(logger, "API called: GET /api/sandbox2/{}", id);
        Sandbox2PersonDtoResponse result = service.getById(id);

        return ResponseEntity.ok(new ApiResponseBody<>(
    		result,
            "getById successfully."
        ));
    }
 
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Sandbox2PersonDtoRequest request) {
        LogUtil.logInfoController(logger, "API called: POST /api/sandbox2");
        Sandbox2PersonDtoResponse result = service.create(request);
        return ResponseEntity.ok(new ApiResponseBody<>(
    		result,
            "create successfully."
        ));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Sandbox2PersonDtoRequest request) {
        LogUtil.logInfoController(logger, "API called: PUT /api/sandbox2/{}", id);
        Sandbox2PersonDtoResponse result = service.updateById(id, request);
        return ResponseEntity.ok(new ApiResponseBody<>(
    		result,
            "updateById successfully."
        ));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        LogUtil.logInfoController(logger, "API called: DELETE /api/sandbox2/{}", id);
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponseBody<>(
    		null,
            "deleteById successfully."
        ));
    }
 
    @GetMapping("/passport/{passportNumber}")
    public ResponseEntity<?> getByPassportNumber(@PathVariable String passportNumber) {
        LogUtil.logInfoController(logger, "API called: GET /api/sandbox2/passport/{}", passportNumber);
        List<Sandbox2PersonDtoResponse> result = service.getByPassportNumber(passportNumber);
        return ResponseEntity.ok(new ApiResponseBody<>(
    		result,
            "getByPassportNumber successfully."
        ));
    }
}
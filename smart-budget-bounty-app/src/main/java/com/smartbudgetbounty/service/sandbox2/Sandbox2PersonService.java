package com.smartbudgetbounty.service.sandbox2;

import java.util.List;

import com.smartbudgetbounty.dto.sandbox2.Sandbox2PersonDtoRequest;
import com.smartbudgetbounty.dto.sandbox2.Sandbox2PersonDtoResponse;

public interface Sandbox2PersonService {
    List<Sandbox2PersonDtoResponse> getAll();
    Sandbox2PersonDtoResponse getById(Long id);
    Sandbox2PersonDtoResponse create(Sandbox2PersonDtoRequest request);
    Sandbox2PersonDtoResponse updateById(Long id, Sandbox2PersonDtoRequest request);
    void deleteById(Long id);
    List<Sandbox2PersonDtoResponse> getByPassportNumber(String passportNumber);
}
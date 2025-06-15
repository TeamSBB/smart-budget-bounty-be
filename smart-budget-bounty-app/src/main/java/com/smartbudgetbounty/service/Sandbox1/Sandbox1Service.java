package com.smartbudgetbounty.service.Sandbox1;

import java.util.List;

import com.smartbudgetbounty.dto.Sandbox1.Sandbox1DtoRequest;
import com.smartbudgetbounty.dto.Sandbox1.Sandbox1DtoResponse;

public interface Sandbox1Service { // Naming convention, is Sandbox1Service for interface and Sandbox1ServiceImpl for class definition. (ISandbox1Service naming is legacy)
	List<Sandbox1DtoResponse> getAll();
	Sandbox1DtoResponse getById(Long id);
	Sandbox1DtoResponse create(Sandbox1DtoRequest request);
	Sandbox1DtoResponse updateById(Long id, Sandbox1DtoRequest request); // This is better practice than without passing in (Long id, request)
	void deleteById(Long id);
}

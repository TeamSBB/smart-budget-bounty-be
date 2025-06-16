package com.smartbudgetbounty.service.sandbox1;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.sandbox1.Sandbox1DtoRequest;
import com.smartbudgetbounty.dto.sandbox1.Sandbox1DtoResponse;
import com.smartbudgetbounty.entity.Sandbox1_NoRelationship;
import com.smartbudgetbounty.repository.Sandbox1NoRelationshipRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Sandbox1ServiceImpl implements Sandbox1Service {

	private static final Logger logger = LoggerFactory
			.getLogger(Sandbox1ServiceImpl.class);

	private final Sandbox1NoRelationshipRepository repo;

	public Sandbox1ServiceImpl(Sandbox1NoRelationshipRepository repo) {
		this.repo = repo;
	}

	private Sandbox1DtoResponse toDtoResponse(Sandbox1_NoRelationship entity) {
		Sandbox1DtoResponse response = new Sandbox1DtoResponse();
		response.setId(entity.getId());
		response.setData(entity.getData());
		return response;
	}
	
	@Override
	public List<Sandbox1DtoResponse> getAll() {
		LogUtil.logStart(logger, "Fetch All Sandbox1_NoRelationship records");
		
		List<Sandbox1DtoResponse> results = repo.findAll()
												.stream()
												.map(this::toDtoResponse).collect(Collectors.toList());
		
		// .stream() 					- converts list into stream (Stream lets u process elements in a functional style
		// .map(this::toDtoResponse) 	- converts entity to DTO
		// .collect 					- collects all the mapped DTO objects into a new list of DTO

		LogUtil.logEnd(logger, "Fetched all Sandbox1_NoRelationship records: {}", results);
		
		return results;
	}

	@Override
	public Sandbox1DtoResponse getById(Long id) {
		LogUtil.logStart(logger, "getById() - Start - Fetch record with ID: {}", id);

		Sandbox1_NoRelationship entity = repo.findById(id)
			 .orElseThrow(() -> {
				 LogUtil.logWarn(logger, "Sandbox1 with ID {} not found", id);
				 throw new EntityNotFoundException("Sandbox1 with ID " + id + " not found");
		});

		Sandbox1DtoResponse response = toDtoResponse(entity);
		
		LogUtil.logEnd(logger, "getById() - End - Fetched record: {}", response);
		
		return response;
	}

	@Override
    public Sandbox1DtoResponse create(Sandbox1DtoRequest request) {
        LogUtil.logStart(logger, "Creating new Sandbox1 with data: {}", request.getData());
        
        Sandbox1_NoRelationship entity = new Sandbox1_NoRelationship();
        entity.setData(request.getData());
        entity.setSecret(request.getSecret());
        
        Sandbox1_NoRelationship saved = repo.save(entity);
        Sandbox1DtoResponse response = toDtoResponse(saved);
        
        LogUtil.logEnd(logger, "Created Sandbox1: {}", response);
        
        return response;
    }
 
    @Override
    public Sandbox1DtoResponse updateById(Long id, Sandbox1DtoRequest request) {
        LogUtil.logStart(logger, "Updating Sandbox1 with ID {} and data: {}", id, request.getData());
        
        Sandbox1_NoRelationship entity = repo.findById(id)
                .orElseThrow(() -> {
                	LogUtil.logWarn(logger, "Sandbox1 with ID {} not found for update", id);
                    return new EntityNotFoundException("Sandbox1 not found");
                });
        entity.setData(request.getData());
        entity.setSecret(request.getSecret());
        
        Sandbox1_NoRelationship updated = repo.save(entity);
        Sandbox1DtoResponse response = toDtoResponse(updated);
        
        LogUtil.logEnd(logger, "Updated Sandbox1: {}", response);
        
        return response;
    }
 
    @Override
    public void deleteById(Long id) {
        LogUtil.logStart(logger, "Deleting Sandbox1 with ID {}", id);
        
        if (!repo.existsById(id)) {
        	LogUtil.logWarn(logger, "Sandbox1 with ID {} not found for deletion", id);
            throw new EntityNotFoundException("Sandbox1 not found");
        }
        repo.deleteById(id);
        
        LogUtil.logEnd(logger, "Deleted Sandbox1 with ID {}", id);
    }

}

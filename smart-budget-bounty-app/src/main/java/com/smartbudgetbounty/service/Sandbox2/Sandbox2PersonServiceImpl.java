package com.smartbudgetbounty.service.Sandbox2;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.Sandbox2.Sandbox2PersonDtoRequest;
import com.smartbudgetbounty.dto.Sandbox2.Sandbox2PersonDtoResponse;
import com.smartbudgetbounty.entity.Sandbox2_OneToOne_Passport;
import com.smartbudgetbounty.entity.Sandbox2_OneToOne_Person;
import com.smartbudgetbounty.repository.Sandbox2PersonRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;
 
@Service
public class Sandbox2PersonServiceImpl implements Sandbox2PersonService {
 
    private static final Logger logger = LoggerFactory.getLogger(Sandbox2PersonServiceImpl.class);
 
    private final Sandbox2PersonRepository repo;
 
    public Sandbox2PersonServiceImpl(Sandbox2PersonRepository repo) {
        this.repo = repo;
    }
 
    @Override
    public Sandbox2PersonDtoResponse create(Sandbox2PersonDtoRequest request) {
        LogUtil.logStart(logger, "Creating new person");
        Sandbox2_OneToOne_Passport passport = new Sandbox2_OneToOne_Passport();
        passport.setPassportNumber(request.getPassportNumber());
 
        Sandbox2_OneToOne_Person person = new Sandbox2_OneToOne_Person();
        person.setName(request.getName());
        person.setPassport(passport);
        passport.setPerson(person);
 
    	Sandbox2_OneToOne_Person saved = repo.save(person);
        Sandbox2PersonDtoResponse response = toResponse(saved);
        LogUtil.logEnd(logger, "Created person: {}", response);
        return response;
    }
 
    @Override
    public List<Sandbox2PersonDtoResponse> getAll() {
        LogUtil.logStart(logger, "Fetching all persons");
        List<Sandbox2PersonDtoResponse> results = repo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        LogUtil.logEnd(logger, "Fetched persons: {}", results);
        return results;
    }
 
    @Override
    public Sandbox2PersonDtoResponse getById(Long id) {
        LogUtil.logStart(logger, "Fetching person with ID {}", id);
        Sandbox2_OneToOne_Person entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with ID " + id));
        Sandbox2PersonDtoResponse response = toResponse(entity);
        LogUtil.logEnd(logger, "Fetched person: {}", response);
        return response;
    }
 
    @Override
    public Sandbox2PersonDtoResponse updateById(Long id, Sandbox2PersonDtoRequest request) {
        LogUtil.logStart(logger, "Updating person with ID {}", id);
        Sandbox2_OneToOne_Person person = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with ID " + id));
 
        person.setName(request.getName());
        if (person.getPassport() != null) {
            person.getPassport().setPassportNumber(request.getPassportNumber());
        }
        Sandbox2_OneToOne_Person updated = repo.save(person);
        Sandbox2PersonDtoResponse response = toResponse(updated);
        LogUtil.logEnd(logger, "Updated person: {}", response);
        return response;
    }
 
    @Override
    public void deleteById(Long id) {
        LogUtil.logStart(logger, "Deleting person with ID {}", id);
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Person not found with ID " + id);
        }
        repo.deleteById(id);
        LogUtil.logEnd(logger, "Deleted person with ID {}", id);
    }
 
    @Override
    public List<Sandbox2PersonDtoResponse> getByPassportNumber(String passportNumber) {
        LogUtil.logStart(logger, "Fetching persons with passport number {}", passportNumber);
        List<Sandbox2PersonDtoResponse> results = repo.findByPassportNumber(passportNumber)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        LogUtil.logEnd(logger, "Fetched {} persons with passport number {}", results.size(), passportNumber);
        return results;
    }
 
    private Sandbox2PersonDtoResponse toResponse(Sandbox2_OneToOne_Person entity) {
        Sandbox2PersonDtoResponse response = new Sandbox2PersonDtoResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        if (entity.getPassport() != null) {
            response.setPassportNumber(entity.getPassport().getPassportNumber());
        }
        return response;
    }
}
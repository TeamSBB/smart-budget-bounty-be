package com.smartbudgetbounty.service.transaction;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoRequest;
import com.smartbudgetbounty.dto.transaction.CreateTransactionDtoResponse;
import com.smartbudgetbounty.entity.Transaction;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.TransactionRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	private final TransactionRepository transactionRepo;
	private final UserRepository userRepo;
	
	public TransactionServiceImpl(TransactionRepository transactionRepo, UserRepository userRepo) {
		this.transactionRepo = transactionRepo;
		this.userRepo = userRepo;
	}
	
	@Override
	public CreateTransactionDtoResponse create(CreateTransactionDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transaction.");
		
        Optional<User> u = userRepo.findById(request.getUserId());
        
        if(u.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find userId: {}.", request.getUserId());
         throw new EntityNotFoundException("Unable to find userId: " + request.getUserId());
        }
        
        Instant now = Instant.now();
        Transaction entity = transactionRepo.save(new Transaction(
    		request.getTransactionAmount(),
    		now,
    		request.getRecipientName(),
    		request.getPaymentMethod(),
    		request.getPaynowRecipient(),
    		request.getAccountNumber(),
    		request.getRemarks(),
    		request.getTransferDate() != null ? request.getTransferDate() :  Instant.now(),
    		u.get()
		));
        
        // TODO: call RewardPointsTransactionServiceImpl.createEarn

        LogUtil.logEnd(logger, "Created Transaction: {}", entity);
        
        
		return new CreateTransactionDtoResponse(
			entity.getId(), 
			entity.getTransactionAmount(), 
			entity.getRecipientName(), 
			now,
			request.getTransferDate() != null ? request.getTransferDate() : now,
			entity.getPaymentMethod(),
			entity.getPaynowRecipient(),
			entity.getAccountNumber(),
			entity.getRemarks()
		);
	}

}

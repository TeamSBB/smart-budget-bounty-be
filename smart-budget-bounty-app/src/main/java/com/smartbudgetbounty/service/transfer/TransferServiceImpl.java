package com.smartbudgetbounty.service.transfer;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.CreateTransferDtoResponse;
import com.smartbudgetbounty.entity.PaymentMethod;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.PaymentMethodRepository;
import com.smartbudgetbounty.repository.TransferRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransferServiceImpl implements TransferService {

	private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

	private final TransferRepository transactionRepo;
	private final UserRepository userRepo;
	private final PaymentMethodRepository paymentMethodRepo;
	
	public TransferServiceImpl(TransferRepository transactionRepo, UserRepository userRepo, PaymentMethodRepository paymentMethodRepo) {
		this.transactionRepo = transactionRepo;
		this.userRepo = userRepo;
		this.paymentMethodRepo = paymentMethodRepo;
	}
	
	@Override
	public CreateTransferDtoResponse create(CreateTransferDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transaction.");
		
        Optional<User> u = userRepo.findById(request.getUserId());
        
        if(u.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find userId: {}.", request.getUserId());
         throw new EntityNotFoundException("Unable to find userId: " + request.getUserId());
        }

        Optional<PaymentMethod> paymentMethod = paymentMethodRepo.findById(request.getPaymentMethodId());
        
        if(paymentMethod.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find paymentId: {}.", request.getPaymentMethodId());
         throw new EntityNotFoundException("Unable to find paymentId: " + request.getPaymentMethodId());
        }
        
        Instant now = Instant.now();
        Transfer entity = transactionRepo.save(new Transfer(
        	
    		request.getTransactionAmount(),
    		now,
    		request.getRecipientName(),
    		request.getFromPaynowPhoneNumber(),
    		request.getToPaynowPhoneNumber(),
    		request.getFromAccountNumber(),
    		request.getToAccountNumber(),
    		request.getBeneficiaryName(),
    		request.getRemarks(),
    		request.getTransferDate() != null ? request.getTransferDate() :  Instant.now(),
    		u.get(),
    		paymentMethod.get()
		));

        LogUtil.logEnd(logger, "Created Transaction: {}", entity.getId());
		
		return new CreateTransferDtoResponse(
			entity.getId(), 
			entity.getRecipientName(), 
			entity.getFromPaynowPhoneNumber(),
			entity.getToPaynowPhoneNumber(),
			request.getTransferDate() != null ? request.getTransferDate() : now,
			entity.getBeneficiaryName(),
			entity.getFromAccountNumber(),
			entity.getToAccountNumber(),
			entity.getTransactionAmount(), 
			now,
			request.getPaymentMethodId(),
			entity.getRemarks()
		);
	}

}

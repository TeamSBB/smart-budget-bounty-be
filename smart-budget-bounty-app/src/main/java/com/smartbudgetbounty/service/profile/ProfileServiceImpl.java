package com.smartbudgetbounty.service.profile;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbudgetbounty.dto.profile.GetProfileInfoDtoResponse;
import com.smartbudgetbounty.dto.profile.UpsertProfileBankInfoDtoRequest;
import com.smartbudgetbounty.dto.profile.UpsertProfileBankInfoDtoResponse;
import com.smartbudgetbounty.dto.profile.UpsertProfileCardInfoDtoRequest;
import com.smartbudgetbounty.dto.profile.UpsertProfileCardInfoDtoResponse;
import com.smartbudgetbounty.dto.profile.UpsertProfilePaynowInfoDtoRequest;
import com.smartbudgetbounty.dto.profile.UpsertProfilePaynowInfoDtoResponse;
import com.smartbudgetbounty.entity.Account;
import com.smartbudgetbounty.entity.BankAccount;
import com.smartbudgetbounty.entity.CreditDebitCard;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.AccountRepository;
import com.smartbudgetbounty.repository.BankAccountRepository;
import com.smartbudgetbounty.repository.CreditDebitCardRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

	private final CreditDebitCardRepository cardRepo;
	private final BankAccountRepository bankRepo;
	private final AccountRepository accountRepo;
	private final UserRepository userRepo;
	
	public ProfileServiceImpl(CreditDebitCardRepository cardRepo,
			BankAccountRepository bankRepo, AccountRepository accountRepo,
			UserRepository userRepo) {
		super();
		this.cardRepo = cardRepo;
		this.bankRepo = bankRepo;
		this.accountRepo = accountRepo;
		this.userRepo = userRepo;
	}


	@Override
	public GetProfileInfoDtoResponse getProfileInfo(Long accountId) {
		LogUtil.logStart(logger, "Start getProfileInfo.");
		
        Optional<Account> account = accountRepo.findById(accountId);
        
        if(account.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find accountId: {}.", accountId);
         throw new EntityNotFoundException("Unable to find accountId: " + accountId);
        }

        BankAccount bankAccount = account.get().getBankAccount();
        CreditDebitCard card = account.get().getCreditDebitCard();
        User u = account.get().getUser();

		LogUtil.logStart(logger, "End getProfileInfo.");
		
        return new GetProfileInfoDtoResponse(
    		bankAccount != null ? bankAccount.getBranchName(): null,
			bankAccount != null ? bankAccount.getBankAccountNumber() : null,
			bankAccount != null ? bankAccount.getBankKey() : null,
			bankAccount != null ? bankAccount.getBeneficiaryName() : null,
			bankAccount != null ? bankAccount.getAccountType() : null,
			card != null ? card.getCardNumber() : null,
			card != null ? card.getCardHolderName() : null,
			card != null ? card.getExpiryDate() : null,
			card != null ? card.getBillingAddress() : null,
        	u != null ? u.getContactNumber() : null
		);

	}

	@Transactional
	@Override
	public UpsertProfileBankInfoDtoResponse upsertBankInfo(
			UpsertProfileBankInfoDtoRequest request) {
        LogUtil.logStart(logger, "Start Upsert Profile Bank Info.");
		
        Optional<Account> account = accountRepo.findById(request.getAccountId());
        
        if(account.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find accountId: {}.", request.getAccountId());
         throw new EntityNotFoundException("Unable to find accountId: " + request.getAccountId());
        }

        BankAccount bankAccount = account.get().getBankAccount();
    	if(bankAccount != null) {
    		// Update
    		bankAccount.setAccountType(request.getAccountType());
    		bankAccount.setBankAccountNumber(request.getBankAccountNumber());
    		bankAccount.setBankKey(request.getBankKey());
    		bankAccount.setBeneficiaryName(request.getBeneficiaryName());
    		bankAccount.setBranchName(request.getBranchName());
    		bankAccount.setUpdatedAt(Instant.now());

    		// Update Bank Table and Account Table
    		BankAccount updatedBankAccount = bankRepo.save(bankAccount);
            LogUtil.logEnd(logger, "Updated Bank Table: {}", bankAccount);
            
    		accountRepo.save(account.get());
            LogUtil.logEnd(logger, "Updated Account Table: {}", account.get());
            
            return new UpsertProfileBankInfoDtoResponse(
            		updatedBankAccount.getId(),
            		updatedBankAccount.getBranchName(),
            		updatedBankAccount.getBankAccountNumber(),
            		updatedBankAccount.getBankKey(),
            		updatedBankAccount.getBeneficiaryName(),
            		updatedBankAccount.getAccountType(),
            		updatedBankAccount.getCreatedAt(),
            		updatedBankAccount.getUpdatedAt()
    		);
    	} 
    	else {
        	// Insert
    		Instant now = Instant.now();
    		bankAccount = new BankAccount(
    					request.getBranchName(),
    					request.getBankAccountNumber(),
    					request.getBankKey(),
    					request.getBeneficiaryName(),
    					request.getAccountType(),
    					now,
    					now
			);
    		// Insert to Bank Table
    		BankAccount newBankAccount = bankRepo.save(bankAccount);
            LogUtil.logEnd(logger, "Insert into Bank Table: {}", bankAccount);
            
    		// Update Account Table
    		account.get().setBankAccount(bankAccount);
    		accountRepo.save(account.get());
            LogUtil.logEnd(logger, "Updated Account Table: {}", account.get());
            
            return new UpsertProfileBankInfoDtoResponse(
            		newBankAccount.getId(),
            		newBankAccount.getBranchName(),
            		newBankAccount.getBankAccountNumber(),
            		newBankAccount.getBankKey(),
            		newBankAccount.getBeneficiaryName(),
            		newBankAccount.getAccountType(),
            		newBankAccount.getCreatedAt(),
            		newBankAccount.getUpdatedAt()
    		);
    	}
	}

	@Transactional
	@Override
	public UpsertProfileCardInfoDtoResponse upsertCardInfo(
			UpsertProfileCardInfoDtoRequest request) {
		LogUtil.logStart(logger, "Start Upsert Profile Card Info.");
		
        Optional<Account> account = accountRepo.findById(request.getAccountId());
        
        if(account.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find accountId: {}.", request.getAccountId());
         throw new EntityNotFoundException("Unable to find accountId: " + request.getAccountId());
        }

        CreditDebitCard card = account.get().getCreditDebitCard();
    	if(card != null) {
    		// Update
    		card.setBillingAddress(request.getBillingAddress());
    		card.setCardHolderName(request.getCardHolderName());
    		card.setCardNumber(request.getCardNumber());
    		card.setExpiryDate(request.getExpiryDate());
    		card.setUpdatedAt(Instant.now());

    		// Update Credit Debit Table and Account Table
    		CreditDebitCard updatedCard = cardRepo.save(card);
            LogUtil.logEnd(logger, "Updated Card Table: {}", updatedCard);
            
    		accountRepo.save(account.get());
            LogUtil.logEnd(logger, "Updated Account Table: {}", account.get());
            
            return new UpsertProfileCardInfoDtoResponse(
            	updatedCard.getId(),
            	updatedCard.getCardNumber(),
            	updatedCard.getCardHolderName(),
            	updatedCard.getExpiryDate(),
            	updatedCard.getBillingAddress(),
            	updatedCard.getCreatedAt(),
            	updatedCard.getUpdatedAt()
    		);
    	} 
    	else {
        	// Insert
    		Instant now = Instant.now();    		
    		card = new CreditDebitCard(
    			request.getCardNumber(),
    			request.getCardHolderName(),
    			request.getExpiryDate(),
    			request.getBillingAddress(),
    			now,
    			now
			);
    		
    		// Insert to Card Table
    		CreditDebitCard newCard = cardRepo.save(card);
            LogUtil.logEnd(logger, "Insert into CreditDebitCard Table: {}", newCard);
            
    		// Update Account Table
    		account.get().setCreditDebitCard(newCard);;
    		accountRepo.save(account.get());
            LogUtil.logEnd(logger, "Updated Account Table: {}", account.get());
            
            return new UpsertProfileCardInfoDtoResponse(
        		newCard.getId(),
        		newCard.getCardNumber(),
        		newCard.getCardHolderName(),
        		newCard.getExpiryDate(),
        		newCard.getBillingAddress(),
        		newCard.getCreatedAt(),
        		newCard.getUpdatedAt()
    		);
    	}
	}

	@Transactional
	@Override
	public UpsertProfilePaynowInfoDtoResponse upsertPaynowInfo(
			UpsertProfilePaynowInfoDtoRequest request) {
		LogUtil.logStart(logger, "Start Upsert Profile Paynow Info.");
		
        Optional<Account> account = accountRepo.findById(request.getAccountId());
        
        if(account.isEmpty()) {
    	 LogUtil.logError(logger, "Unable to find accountId: {}.", request.getAccountId());
         throw new EntityNotFoundException("Unable to find accountId: " + request.getAccountId());
        }

        User u = account.get().getUser();
    	if(u != null) {
    		// Update
    		u.setContactNumber(request.getPhoneNumber());
    		u.setUpdatedAt(Instant.now());
    		
    		// Update User Table and Account Table
    		User updatedUser = userRepo.save(u);
            LogUtil.logEnd(logger, "Updated User Table: {}", updatedUser);

    		accountRepo.save(account.get());
            LogUtil.logEnd(logger, "Updated Account Table: {}", account.get());
            
            return new UpsertProfilePaynowInfoDtoResponse(
            	updatedUser.getId(),
            	updatedUser.getContactNumber()
    		);
    	} 
    	else {
    		throw new EntityNotFoundException("Unable to find userId from this accountId: " + request.getAccountId());
    	}
	}

}

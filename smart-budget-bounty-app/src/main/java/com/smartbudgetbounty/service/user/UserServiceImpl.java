package com.smartbudgetbounty.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.smartbudgetbounty.dto.auth.LoginDtoRequest;
import com.smartbudgetbounty.dto.auth.LoginDtoResponse;
import com.smartbudgetbounty.dto.auth.RegisterDtoRequest;
import com.smartbudgetbounty.dto.auth.RegisterDtoResponse;
import com.smartbudgetbounty.entity.Account;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.AccountRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.service.jwt.JwtService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AuthenticationManager authenticationManager;    
    private final PasswordEncoder encoder;    
    private final JwtService jwtService;
    
    private final UserRepository userRepo;    
    private final AccountRepository accountRepo;

	public UserServiceImpl(AuthenticationManager authenticationManager,
			UserRepository userRepo, PasswordEncoder encoder,
			JwtService jwtService, AccountRepository accountRepo) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.jwtService = jwtService;
		this.accountRepo = accountRepo;
	}
	
	@Override
	public LoginDtoResponse loginUser(LoginDtoRequest loginUserDtoRequest) {
		LogUtil.logStart(logger, "Start loginUser");
		
		// Check email and password
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
        		loginUserDtoRequest.getEmail(),
        		loginUserDtoRequest.getPassword()
            )
        );
        
        // If success, generate jwt token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();        
        String token = jwtService.generateToken(userDetails.getUsername());
        
        User u = userRepo.findByEmail(userDetails.getUsername()); // Confusing part, the username is the email

        // Locate accountId
        Account account = accountRepo.findByUserId(u.getId());
        if(account == null) {
        	throw new EntityNotFoundException("Account could not be found using userId: " + u.getId());
        }
        
		LogUtil.logEnd(logger, "End loginUser user: {}, accountId: {}", u, account.getId());
		
        // Format DTO and return response.
        return new LoginDtoResponse(
        		token,
        		u.getId(),
        		account.getId(),
        		u.getUsername(),
        		u.getEmail(), 
        		u.getAddress(), 
        		u.getContactNumber(), 
        		u.getFirstName(), 
        		u.getLastName()
		);
	}

	@Override
	public RegisterDtoResponse registerUser(RegisterDtoRequest registerUserDto) {
		LogUtil.logStart(logger, "Start registerUser");
		// User already exist, cannot register user.
        if (userRepo.existsByEmail(registerUserDto.getEmail())) {
             LogUtil.logErrorController(logger, "Email is already taken.");
             throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already taken.");
        }
        
        if (userRepo.existsByUsername(registerUserDto.getUsername())) {
            LogUtil.logErrorController(logger, "Username is already taken.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already taken.");
       }
        
        
        // User don't exist, register user.
        User u = userRepo.save(new User(
	                registerUserDto.getUsername(),
	                encoder.encode(registerUserDto.getPassword()),
	        		registerUserDto.getAddress(),
	        		registerUserDto.getContactNumber(),
	        		registerUserDto.getEmail(),
	        		registerUserDto.getFirstName(),
	        		registerUserDto.getLastName()
        		)
		);

        // Create Account first
        Account account = accountRepo.save(new Account(
    		null, 
    		null, 
    		u, 
    		0
		));

		LogUtil.logEnd(logger, "End registerUser user: {}, account: {}", u, account);
		
		return new RegisterDtoResponse(u.getUsername());
	}

	@Override
	public User getById(Long id) {
	    LogUtil.logStart(logger, "Getting User by id.");
	    
        User user = userRepo.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find userId: {}.", id);
            return new EntityNotFoundException("Unable to find userId: " + id);
        });
        
        LogUtil.logEnd(logger, "Retrieved User: {}", user);
        
	    return user;
	}
}

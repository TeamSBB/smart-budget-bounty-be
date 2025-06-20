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
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.service.jwt.JwtService;
import com.smartbudgetbounty.util.LogUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AuthenticationManager authenticationManager;    
    private final UserRepository userRepository;    
    private final PasswordEncoder encoder;    
    private final JwtService jwtService;

	public UserServiceImpl(AuthenticationManager authenticationManager,
			UserRepository userRepository, PasswordEncoder encoder,
			JwtService jwtService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.jwtService = jwtService;
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
        
        User u = userRepository.findByEmail(userDetails.getUsername()); // Confusing part, the username is the email

		LogUtil.logEnd(logger, "End loginUser user: {}", u);
		
        // Format DTO and return response.
        return new LoginDtoResponse(
        		token, 
        		u.getId(),
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
        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
             LogUtil.logErrorController(logger, "Email is already taken.");
             throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already taken.");
        }
        
        if (userRepository.existsByUsername(registerUserDto.getUsername())) {
            LogUtil.logErrorController(logger, "Username is already taken.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already taken.");
       }
        
        // User don't exist, register user.
        User u = userRepository.save(new User(
	                null,
	                registerUserDto.getUsername(),
	                encoder.encode(registerUserDto.getPassword()),
	        		registerUserDto.getAddress(),
	        		registerUserDto.getContactNumber(),
	        		registerUserDto.getEmail(),
	        		registerUserDto.getFirstName(),
	        		registerUserDto.getLastName()
        		)
		);

		LogUtil.logEnd(logger, "End registerUser user: {}", u);
		
		return new RegisterDtoResponse(u.getUsername());
	}

}

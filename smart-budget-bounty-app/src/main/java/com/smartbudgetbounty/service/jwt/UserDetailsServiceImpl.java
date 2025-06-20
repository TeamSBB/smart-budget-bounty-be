package com.smartbudgetbounty.service.jwt;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.repository.UserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    private final UserRepository userRepository;
    
    public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        com.smartbudgetbounty.entity.User user = userRepository.findByUsername(username);
        com.smartbudgetbounty.entity.User user = userRepository.findByEmail(email);
        
        if (user == null) {
//            throw new UsernameNotFoundException("User Not Found with username: " + username);
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }
        
        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
        		user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
package com.smartbudgetbounty.user;

import com.smartbudgetbounty.dto.auth.LoginDtoRequest;
import com.smartbudgetbounty.dto.auth.LoginDtoResponse;
import com.smartbudgetbounty.dto.auth.RegisterDtoRequest;
import com.smartbudgetbounty.dto.auth.RegisterDtoResponse;
import com.smartbudgetbounty.entity.Account;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.AccountRepository;
import com.smartbudgetbounty.repository.UserRepository;
import com.smartbudgetbounty.service.jwt.JwtService;
import com.smartbudgetbounty.service.user.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepo;

    @Mock
    private AccountRepository accountRepo;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginUser_Success() {
        // Arrange
        LoginDtoRequest request = new LoginDtoRequest("test@example.com", "password");

        Authentication auth = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        User user = new User("username", "password", "address", "12345678", "test@example.com", "First", "Last");
        user.setId(1L);

        Account account = new Account(null, null, user, 0);
        account.setId(100L);

        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("test@example.com");
        when(jwtService.generateToken("test@example.com")).thenReturn("jwt-token");
        when(userRepo.findByEmail("test@example.com")).thenReturn(user);
        when(accountRepo.findByUserId(1L)).thenReturn(account);
        when(userRepo.save(any())).thenReturn(user);

        // Act
        LoginDtoResponse response = userService.loginUser(request);

        // Assert
        assertEquals("jwt-token", response.getToken());
        assertEquals(1L, response.getUserId());
        assertEquals(100L, response.getAccountId());
        assertEquals("username", response.getUsername());
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        RegisterDtoRequest request = new RegisterDtoRequest(
                "newuser", "password", "address", "12345678", "new@example.com", "First", "Last"
        );

        User user = new User(
                request.getUsername(),
                "encoded-password",
                request.getAddress(),
                request.getContactNumber(),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName()
        );
        user.setId(1L);

        Account account = new Account(null, null, user, 0);
        account.setId(100L);

        when(userRepo.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepo.existsByUsername(request.getUsername())).thenReturn(false);
        when(encoder.encode(request.getPassword())).thenReturn("encoded-password");
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(accountRepo.save(any(Account.class))).thenReturn(account);

        // Act
        RegisterDtoResponse response = userService.registerUser(request);

        // Assert
        assertEquals("newuser", response.getUsername());
        verify(userRepo).save(any(User.class));
        verify(accountRepo).save(any(Account.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyTaken() {
        // Arrange
        RegisterDtoRequest request = new RegisterDtoRequest(
                "newuser", "password", "address", "12345678", "existing@example.com", "First", "Last"
        );

        when(userRepo.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            userService.registerUser(request);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Email is already taken"));
    }

    @Test
    void testRegisterUser_UsernameAlreadyTaken() {
        // Arrange
        RegisterDtoRequest request = new RegisterDtoRequest(
                "existinguser", "password", "address", "12345678", "new@example.com", "First", "Last"
        );

        when(userRepo.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepo.existsByUsername(request.getUsername())).thenReturn(true);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            userService.registerUser(request);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Username is already taken"));
    }


    @Test
    void testGetById_UserExists() {
        // Arrange
        User user = new User("username", "password", "address", "12345678", "email@example.com", "First", "Last");
        user.setId(1L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("username", result.getUsername());
    }

    @Test
    void testGetById_UserNotFound() {
        // Arrange
        when(userRepo.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.getById(999L);
        });

        assertEquals("Unable to find userId: 999", exception.getMessage());
    }


}

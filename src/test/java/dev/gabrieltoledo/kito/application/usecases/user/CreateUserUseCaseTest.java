package dev.gabrieltoledo.kito.application.usecases.user;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.gabrieltoledo.kito.application.exceptions.EmailAlreadyExistsException;
import dev.gabrieltoledo.kito.domain.user.User;
import dev.gabrieltoledo.kito.domain.user.UserRole;
import dev.gabrieltoledo.kito.repositories.interfaces.UserRepository;
import dev.gabrieltoledo.kito.web.dtos.request.CreateUserRequest;
import dev.gabrieltoledo.kito.web.dtos.response.UserResponse;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private CreateUserRequest request;

    @BeforeEach
    void setUp() {
         request = new CreateUserRequest("John Doe", "john@doe.com", "password123");
    }

    @Test
    @DisplayName("Should create user successfully")
    void shouldCreateUserSuccessfully() {
        when(userRepository.findByEmail("john@doe.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");

        var savedUser = new User(
                UUID.randomUUID(),
                "John Doe",
                "john@doe.com",
                "encryptedPassword",
                UserRole.USER,
                null,
                Instant.now(),
                Instant.now()
        );

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponse response = createUserUseCase.execute(request);

        assertNotNull(response);
        assertEquals(savedUser.getId(), response.getId());
        assertEquals(savedUser.getName(), response.getName());
        assertEquals(savedUser.getEmail(), response.getEmail());
        assertEquals(savedUser.getRole(), response.getRole());

        verify(userRepository).findByEmail("john@doe.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Should send encoded password to repository")
    void shouldSendEncodedPasswordToRepository() {
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encryptedPassword");

        var savedUser = new User(
                UUID.randomUUID(),
                "John Doe",
                "john@doe.com",
                "encryptedPassword",
                UserRole.USER,
                null,
                Instant.now(),
                Instant.now()
        );
        
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        createUserUseCase.execute(request);

        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals("encryptedPassword", capturedUser.getPassword());
    }

    @Test
    @DisplayName("Should throw EmailAlreadyExistsException")
    void shouldThrowEmailAlreadyExistsException() {

        var existingUser = new User(UUID.randomUUID(), "John Doe", "john@doe.com", "any", UserRole.USER, null, Instant.now(), Instant.now());
        when(userRepository.findByEmail("john@doe.com")).thenReturn(Optional.of(existingUser));

        EmailAlreadyExistsException ex = assertThrows(
            EmailAlreadyExistsException.class,
            () -> createUserUseCase.execute(request)
        );

        verify(userRepository).findByEmail("john@doe.com");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));

        assertEquals("Email already exists", ex.getMessage());
    }
}

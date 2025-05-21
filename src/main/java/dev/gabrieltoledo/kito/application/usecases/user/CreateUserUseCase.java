package dev.gabrieltoledo.kito.application.usecases.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gabrieltoledo.kito.application.exceptions.EmailAlreadyExistsException;
import dev.gabrieltoledo.kito.domain.user.User;
import dev.gabrieltoledo.kito.domain.user.UserRole;
import dev.gabrieltoledo.kito.repositories.interfaces.UserRepository;
import dev.gabrieltoledo.kito.web.dtos.request.CreateUserRequest;
import dev.gabrieltoledo.kito.web.dtos.response.UserResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse execute(CreateUserRequest user) {

        this.userRepository.findByEmail(user.getEmail())
        .ifPresent(userWithSameEmail -> { throw new EmailAlreadyExistsException(); });

        var encryptedPassword = this.passwordEncoder.encode(user.getPassword());

        User domain = new User(null, user.getName(), user.getEmail(), encryptedPassword, UserRole.USER, null, null, null);

        User createdUser = this.userRepository.save(domain);

        return UserResponse.build(createdUser.getId(), createdUser.getName(), createdUser.getEmail(), createdUser.getRole(), createdUser.getCreatedAt());
    }
}

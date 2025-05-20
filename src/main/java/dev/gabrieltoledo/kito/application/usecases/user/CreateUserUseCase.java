package dev.gabrieltoledo.kito.application.usecases.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gabrieltoledo.kito.application.exceptions.EmailAlreadyExistsException;
import dev.gabrieltoledo.kito.domain.user.User;
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
        User domain = user.toDomain();

        this.userRepository.findByEmail(domain.getEmail())
        .ifPresent(userWithSameEmail -> { throw new EmailAlreadyExistsException(); });

        var encryptedPassword = this.passwordEncoder.encode(domain.getPassword());
        domain.setPassword(encryptedPassword);

        User createdUser = this.userRepository.save(domain);

        return UserResponse.build(createdUser);
    }
}

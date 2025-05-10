package dev.gabrieltoledo.kito.application.usecases.user;

import org.springframework.stereotype.Service;

import dev.gabrieltoledo.kito.application.exceptions.EmailAlreadyExistsException;
import dev.gabrieltoledo.kito.domain.models.User;
import dev.gabrieltoledo.kito.domain.repositories.UserRepository;
import dev.gabrieltoledo.kito.infrastructure.entities.UserEntity;
import dev.gabrieltoledo.kito.web.dtos.request.CreateUserDTO;
import dev.gabrieltoledo.kito.web.dtos.response.UserResponseDTO;
import dev.gabrieltoledo.kito.web.mappers.UserDTOMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserDTOMapper mapper;

    public UserResponseDTO execute(CreateUserDTO user) {
        User domain = mapper.toDomain(user);

        var userWithSameEmail = this.userRepository.findByEmail(domain.getEmail());

        if (userWithSameEmail.isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        // # PENDENT: encrypt password

        var createdUser = this.userRepository.save(UserEntity.newInstance(domain));

        var dsadas = createdUser.toDomain();

        return mapper.toResponse(dsadas);
    }
}

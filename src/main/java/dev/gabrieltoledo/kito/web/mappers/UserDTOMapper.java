package dev.gabrieltoledo.kito.web.mappers;

import org.mapstruct.Mapper;

import dev.gabrieltoledo.kito.domain.models.User;
import dev.gabrieltoledo.kito.web.dtos.request.CreateUserDTO;
import dev.gabrieltoledo.kito.web.dtos.response.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    default User toDomain(CreateUserDTO dto) {
        return new User(null, dto.name(), dto.email(), dto.password(), null, null, null);
    }

    UserResponseDTO toResponse(User user);
}

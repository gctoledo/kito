package dev.gabrieltoledo.kito.web.dtos.response;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email) {

}
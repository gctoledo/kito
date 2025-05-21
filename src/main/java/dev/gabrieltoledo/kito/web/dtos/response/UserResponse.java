package dev.gabrieltoledo.kito.web.dtos.response;

import java.time.Instant;
import java.util.UUID;

import dev.gabrieltoledo.kito.domain.user.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private Instant createdAt;

    public static UserResponse build(UUID id, String name, String email, UserRole role, Instant createdAt) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setName(name);
        userResponse.setEmail(email);
        userResponse.setRole(role);
        userResponse.setCreatedAt(createdAt);

        return userResponse;
    }
}

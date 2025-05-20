package dev.gabrieltoledo.kito.web.dtos.response;

import java.time.Instant;
import java.util.UUID;

import dev.gabrieltoledo.kito.domain.user.User;
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

    public static UserResponse build(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        userResponse.setCreatedAt(user.getCreatedAt());

        return userResponse;
    }
}

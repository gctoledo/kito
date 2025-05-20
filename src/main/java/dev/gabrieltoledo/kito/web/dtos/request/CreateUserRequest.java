package dev.gabrieltoledo.kito.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 100, message = "First name must between 3 and 100 characters")
    private final String name;

    @NotBlank(message = "The email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private final String email;

    @NotBlank(message = "The password is required")
    @Size(min = 8, max = 255, message = "Password must be at least 8 characters")
    private final String password;
}
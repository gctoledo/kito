package dev.gabrieltoledo.kito.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrieltoledo.kito.application.usecases.user.CreateUserUseCase;
import dev.gabrieltoledo.kito.web.dtos.request.CreateUserRequest;
import dev.gabrieltoledo.kito.web.dtos.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest user) {
        var response = this.createUserUseCase.execute(user);

        return ResponseEntity.ok(response);
    }
}

package dev.gabrieltoledo.kito.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrieltoledo.kito.application.usecases.user.CreateUserUseCase;
import dev.gabrieltoledo.kito.web.dtos.request.CreateUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateUserDTO user) {
        var response = createUserUseCase.execute(user);

        return ResponseEntity.ok(response);
    }
}

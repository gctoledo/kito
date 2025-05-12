package dev.gabrieltoledo.kito.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrieltoledo.kito.application.usecases.transaction.CreateTransactionUseCase;
import dev.gabrieltoledo.kito.web.dtos.request.CreateTransactionDTO;
import dev.gabrieltoledo.kito.web.dtos.response.TransactionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final CreateTransactionUseCase createTransactionUseCase;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@Valid @RequestBody CreateTransactionDTO transaction) {
        var response = createTransactionUseCase.execute(transaction);

        return ResponseEntity.ok(response);
    }
}

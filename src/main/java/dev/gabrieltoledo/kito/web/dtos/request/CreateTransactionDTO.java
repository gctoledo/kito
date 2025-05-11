package dev.gabrieltoledo.kito.web.dtos.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import dev.gabrieltoledo.kito.domain.models.enums.PaymentMethod;
import dev.gabrieltoledo.kito.domain.models.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateTransactionDTO(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Transaction type is required")
        TransactionType transactionType,

        @NotNull(message = "Transaction date is required")
        LocalDate transactionDate,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @Size(max = 255, message = "Description must be at most 255 characters")
        String description) {

}
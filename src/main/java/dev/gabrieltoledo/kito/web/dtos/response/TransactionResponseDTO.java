package dev.gabrieltoledo.kito.web.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import dev.gabrieltoledo.kito.domain.models.enums.PaymentMethod;
import dev.gabrieltoledo.kito.domain.models.enums.TransactionType;

public record TransactionResponseDTO(
        UUID id,
        PaymentMethod paymentMethod,
        TransactionType transactionType,
        LocalDate transactionDate,
        BigDecimal amount,
        String description) {
}

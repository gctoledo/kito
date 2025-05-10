package dev.gabrieltoledo.kito.domain.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import dev.gabrieltoledo.kito.domain.models.enums.PaymentMethod;
import dev.gabrieltoledo.kito.domain.models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    private Long id;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private User user;
    private Instant createdAt;
    private Instant updatedAt;
}
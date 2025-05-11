package dev.gabrieltoledo.kito.domain.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import dev.gabrieltoledo.kito.domain.models.enums.PaymentMethod;
import dev.gabrieltoledo.kito.domain.models.enums.TransactionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = { "id", "publicId" })
public class Transaction {

    public Transaction() {
        this.publicId = UUID.randomUUID();
    }

    public Transaction(Long id, PaymentMethod paymentMethod, TransactionType transactionType,
            String description, LocalDate transactionDate, BigDecimal amount, User user, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.publicId = UUID.randomUUID();
        this.paymentMethod = paymentMethod;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public Transaction(Long id, UUID publicId, PaymentMethod paymentMethod, TransactionType transactionType,
            String description, LocalDate transactionDate, BigDecimal amount, User user, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.publicId = publicId;
        this.paymentMethod = paymentMethod;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    private Long id;
    private final UUID publicId;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private User user;
    private Instant createdAt;
    private Instant updatedAt;
}
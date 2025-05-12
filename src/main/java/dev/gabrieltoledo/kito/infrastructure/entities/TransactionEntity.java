package dev.gabrieltoledo.kito.infrastructure.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import dev.gabrieltoledo.kito.domain.models.Transaction;
import dev.gabrieltoledo.kito.domain.models.enums.PaymentMethod;
import dev.gabrieltoledo.kito.domain.models.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false)
    private UUID publicId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Transaction toDomain() {
        return new Transaction(id, publicId, paymentMethod, transactionType, description, transactionDate, amount,
                user.toDomain(), createdAt, updatedAt);
    }

    public static TransactionEntity newInstance(Transaction transaction) {
        UserEntity userEntity = UserEntity.newInstance(transaction.getUser());
        return new TransactionEntity(
                transaction.getId(),
                transaction.getPublicId(),
                transaction.getPaymentMethod(),
                transaction.getTransactionType(),
                transaction.getDescription(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                userEntity,
                transaction.getCreatedAt(),
                transaction.getUpdatedAt());
    }
}

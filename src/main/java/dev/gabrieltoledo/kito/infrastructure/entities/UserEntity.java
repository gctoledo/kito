package dev.gabrieltoledo.kito.infrastructure.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import dev.gabrieltoledo.kito.domain.models.Transaction;
import dev.gabrieltoledo.kito.domain.models.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<TransactionEntity> transactions;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    private List<Transaction> toDomainTransactions() {

        if (transactions != null) {
            return transactions.stream().map(TransactionEntity::toDomain).toList();
        }

        return null;
    }

    public User toDomain() {
        return new User(id, name, email, password, toDomainTransactions(), createdAt, updatedAt);
    }

    public static UserEntity newInstance(User user) {

        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getTransactions() != null
                        ? user.getTransactions().stream()
                                .map(TransactionEntity::newInstance)
                                .toList()
                        : null,
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
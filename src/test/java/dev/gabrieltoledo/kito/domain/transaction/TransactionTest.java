package dev.gabrieltoledo.kito.domain.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import dev.gabrieltoledo.kito.domain.user.User;

class TransactionTest {

    @Test
    void shouldGeneratePublicIdIfNotProvided() {
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        TransactionType transactionType = TransactionType.INCOME;
        String description = "Test transaction";
        LocalDate transactionDate = LocalDate.now();
        BigDecimal amount = new BigDecimal("100.00");
        User user = new User();

        Transaction transaction = new Transaction(
            null,
            null,
            paymentMethod,
            transactionType,
            description,
            transactionDate,
            amount,
            user,
            Instant.now(),
            Instant.now()
        );

        assertThat(transaction.getPublicId()).isNotNull();
        assertThat(transaction.getDescription()).isEqualTo(description);
        assertThat(transaction.getAmount()).isEqualTo(amount);
    }

    @Test
    void shouldKeepPublicIdIfProvided() {
        UUID providedId = UUID.randomUUID();

        Transaction transaction = new Transaction(
            null,
            providedId,
            PaymentMethod.DEBIT_CARD,
            TransactionType.EXPENSE,
            "Compra supermercado",
            LocalDate.of(2025, 5, 19),
            new BigDecimal("89.90"),
            new User(),
            Instant.now(),
            Instant.now()
        );

        assertThat(transaction.getPublicId()).isEqualTo(providedId);
    }
}
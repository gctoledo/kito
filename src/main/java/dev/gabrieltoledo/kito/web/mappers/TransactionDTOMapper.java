package dev.gabrieltoledo.kito.web.mappers;

import org.mapstruct.Mapper;

import dev.gabrieltoledo.kito.domain.models.Transaction;
import dev.gabrieltoledo.kito.infrastructure.entities.TransactionEntity;
import dev.gabrieltoledo.kito.infrastructure.entities.UserEntity;
import dev.gabrieltoledo.kito.web.dtos.request.CreateTransactionDTO;
import dev.gabrieltoledo.kito.web.dtos.response.TransactionResponseDTO;

@Mapper(componentModel = "spring")
public interface TransactionDTOMapper {

    default Transaction toDomain(CreateTransactionDTO dto, UserEntity user) {
        return new Transaction(null, dto.paymentMethod(), dto.transactionType(), dto.description(),
                dto.transactionDate(), dto.amount(), user.toDomain(), null, null);
    }

    default TransactionResponseDTO toResponse(TransactionEntity transaction) {
        return new TransactionResponseDTO(
                transaction.getPublicId(),
                transaction.getPaymentMethod(),
                transaction.getTransactionType(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getDescription());
    }
}

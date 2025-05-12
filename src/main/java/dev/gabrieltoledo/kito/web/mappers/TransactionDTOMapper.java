package dev.gabrieltoledo.kito.web.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import dev.gabrieltoledo.kito.domain.models.Transaction;
import dev.gabrieltoledo.kito.domain.repositories.UserRepository;
import dev.gabrieltoledo.kito.infrastructure.entities.TransactionEntity;
import dev.gabrieltoledo.kito.web.dtos.request.CreateTransactionDTO;
import dev.gabrieltoledo.kito.web.dtos.response.TransactionResponseDTO;

@Mapper(componentModel = "spring")
public abstract class TransactionDTOMapper {

    @Autowired
    private UserRepository userRepository;

    public Transaction toDomain(CreateTransactionDTO dto) {
        var user = userRepository.findById(dto.userId());

        return new Transaction(null, dto.paymentMethod(), dto.transactionType(), dto.description(),
                dto.transactionDate(), dto.amount(), user.get().toDomain(), null, null);
    }

    public TransactionResponseDTO toResponse(TransactionEntity transaction) {
        return new TransactionResponseDTO(
                transaction.getPublicId(),
                transaction.getPaymentMethod(),
                transaction.getTransactionType(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getDescription());
    }
}

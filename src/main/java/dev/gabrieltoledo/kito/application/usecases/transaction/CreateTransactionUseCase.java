package dev.gabrieltoledo.kito.application.usecases.transaction;

import org.springframework.stereotype.Service;

import dev.gabrieltoledo.kito.application.exceptions.UserNotFoundException;
import dev.gabrieltoledo.kito.domain.repositories.TransactionRepository;
import dev.gabrieltoledo.kito.domain.repositories.UserRepository;
import dev.gabrieltoledo.kito.infrastructure.entities.TransactionEntity;
import dev.gabrieltoledo.kito.web.dtos.request.CreateTransactionDTO;
import dev.gabrieltoledo.kito.web.dtos.response.TransactionResponseDTO;
import dev.gabrieltoledo.kito.web.mappers.TransactionDTOMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionDTOMapper mapper;

    public TransactionResponseDTO execute(CreateTransactionDTO transaction) {
        var user = this.userRepository
                        .findById(transaction.userId())
                        .orElseThrow(() -> new UserNotFoundException());

        var domain = mapper.toDomain(transaction, user);

        var entity = TransactionEntity.newInstance(domain);

        var result = this.transactionRepository.save(entity);

        return mapper.toResponse(result);
    }
    
}

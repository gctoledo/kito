package dev.gabrieltoledo.kito.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrieltoledo.kito.infrastructure.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>, ITransactionRepository {

}

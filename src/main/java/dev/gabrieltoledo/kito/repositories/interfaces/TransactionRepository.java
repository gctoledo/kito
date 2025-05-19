package dev.gabrieltoledo.kito.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrieltoledo.kito.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, ITransactionRepository {

}

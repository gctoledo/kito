package dev.gabrieltoledo.kito.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrieltoledo.kito.infrastructure.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID>, IUserRepository {
    Optional<UserEntity> findByEmail(String email);
}

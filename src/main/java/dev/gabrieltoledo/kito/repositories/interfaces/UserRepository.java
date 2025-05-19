package dev.gabrieltoledo.kito.repositories.interfaces;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrieltoledo.kito.domain.user.User;

public interface UserRepository extends JpaRepository<User, UUID>, IUserRepository {
    Optional<User> findByEmail(String email);
}

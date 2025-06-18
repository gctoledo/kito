package dev.gabrieltoledo.kito.repositories.interfaces;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dev.gabrieltoledo.kito.common.BaseRepositoryTest;
import dev.gabrieltoledo.kito.domain.user.User;
import dev.gabrieltoledo.kito.domain.user.UserRole;

public class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save and find user by id")
    void shouldSaveAndFindUserById() {
        User user = new User(null, "John Doe", "john@doe.com", "password", UserRole.USER, null, null, null);

        User createdUser = userRepository.save(user);

        Optional<User> found = userRepository.findById(createdUser.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John Doe");
        assertThat(found.get().getEmail()).isEqualTo("john@doe.com");
    }

    @Test
    @DisplayName("Should find user by email")
    void shouldFindUserByEmail() {
        User user = new User(null, "John Doe", "john@doe.com", "password", UserRole.USER, null, null, null);

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("john@doe.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("john@doe.com");
        assertThat(found.get().getName()).isEqualTo("John Doe");
    }
}

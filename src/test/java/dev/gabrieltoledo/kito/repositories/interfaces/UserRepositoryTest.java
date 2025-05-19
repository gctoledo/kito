package dev.gabrieltoledo.kito.repositories.interfaces;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import dev.gabrieltoledo.kito.domain.user.User;

@DataJpaTest
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:16");

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should save and find user by id")
    void shouldSaveAndFindUserById() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@doe.com");
        user.setPassword("123456");

        User createdUser = userRepository.save(user);;

        Optional<User> found = userRepository.findById(createdUser.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John Doe");
        assertThat(found.get().getEmail()).isEqualTo("john@doe.com");
    }

    @Test
    @DisplayName("Should find user by email")
    void shouldFindUserByEmail() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@doe.com");
        user.setPassword("senha123");

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("john@doe.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("john@doe.com");
        assertThat(found.get().getName()).isEqualTo("John Doe");
    }
}

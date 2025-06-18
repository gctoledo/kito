package dev.gabrieltoledo.kito.web.e2e;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import dev.gabrieltoledo.kito.web.dtos.request.CreateUserRequest;
import dev.gabrieltoledo.kito.web.dtos.response.UserResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserE2ETest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:16");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    @DisplayName("POST /users")
    class CreateUserTests {

        @Test
        @DisplayName("Should create user successfully")
        void shouldCreateUserSuccessfully() {
            CreateUserRequest request = new CreateUserRequest(
                "John Doe",
                "john@doe.com",
                "securePassword123"
            );

            ResponseEntity<UserResponse> response = restTemplate.postForEntity(
                "/users",
                request,
                UserResponse.class
            );

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getId()).isInstanceOf(UUID.class);
            assertThat(response.getBody().getEmail()).isEqualTo("john@doe.com");
        }

        @Test
        @DisplayName("Should return 409 when email already exists")
        void shouldReturn409WhenEmailAlreadyExists() {
            CreateUserRequest request = new CreateUserRequest(
                "John",
                "exists@example.com",
                "securePassword123"
            );

            ResponseEntity<UserResponse> first = restTemplate.postForEntity("/users", request, UserResponse.class);
            assertThat(first.getStatusCode()).isEqualTo(HttpStatus.OK);

            ResponseEntity<String> second = restTemplate.postForEntity("/users", request, String.class);
            assertThat(second.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
            assertThat(second.getBody()).contains("Email already exists");
        }

        @Test
        @DisplayName("Should return 400 when email is invalid")
        void shouldReturn400WhenEmailIsInvalid() {
            CreateUserRequest request = new CreateUserRequest(
                "Gabriel",
                "invalid-email",
                "securePassword123"
            );

            ResponseEntity<String> response = restTemplate.postForEntity("/users", request, String.class);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
            assertThat(response.getBody()).contains("Invalid email format");
        }
    }
}
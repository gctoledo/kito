package dev.gabrieltoledo.kito.domain.models;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;
}
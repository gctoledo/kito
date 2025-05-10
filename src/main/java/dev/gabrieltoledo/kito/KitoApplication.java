package dev.gabrieltoledo.kito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitoApplication.class, args);
	}

}

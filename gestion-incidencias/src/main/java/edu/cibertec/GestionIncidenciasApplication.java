package edu.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "edu.cibertec.repository")
public class GestionIncidenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionIncidenciasApplication.class, args);
	}

}

package br.com.example.juspharus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Jus Pharus", version = "1.0"))
public class JuspharusApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuspharusApplication.class, args);
	}

}

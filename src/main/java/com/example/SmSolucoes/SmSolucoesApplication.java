package com.example.SmSolucoes;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Segurança a ser utilizada que será o Keycloack, enviando o token do usuário no esquema Bearer
@SecurityScheme(
		name = "Keycloack",
		openIdConnectUrl = "http://localhost:8081/realms/orcamento/.well-known/openid-configuration",
		scheme = "bearer",
		type = SecuritySchemeType.OPENIDCONNECT,
		in = SecuritySchemeIn.HEADER
)
public class SmSolucoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmSolucoesApplication.class, args);
	}

}

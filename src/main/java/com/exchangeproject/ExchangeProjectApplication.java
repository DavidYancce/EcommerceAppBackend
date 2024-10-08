package com.exchangeproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "bearer", in = SecuritySchemeIn.HEADER, name = "bearerAuth")
@OpenAPIDefinition(info = @Info(title = "Exchange project documentation", version = "1.0.0"), security = { @SecurityRequirement(name = "bearerAuth") })
public class ExchangeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeProjectApplication.class, args);
	}

}

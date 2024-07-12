package com.med.todo;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@OpenAPIDefinition(
	info= @Info(
			title= "Spring Security Todo app API Documentation",
			description = "Spring Security Todo app API Documentation",
			version = "v1.0",
			contact = @Contact(
					name="Medha",
					email="medhasoman13@gmail.com"
			)

	),
		externalDocs = @ExternalDocumentation(
				description = "REST API documentation for the project -A guide for securing Rest APIs using Spring security and JWT for a Todo App"
		)
)

//Securing Rest APIs using Spring security and JWT for a Todo App
@SpringBootApplication
public class TodoManagementApplication {

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoManagementApplication.class, args);
	}

}

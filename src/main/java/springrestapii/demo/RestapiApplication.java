package springrestapii.demo;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:env.properties")
@OpenAPIDefinition(info = @Info(
		title = "Spring Boot RestAPI.",
		description = "Developed using Spring Mvc, Jpa, Mysql connector and 3rd party libraries such as Lombok, MapStruct, Spring-validation, SpringBoot actuator & SpringDoc openAPI.",
		version = "1.0.0",
		contact = @Contact(
				name = "Sourav Kumar",
				email = "isauravshing@gmail.com",
				url = "https://sauraevshing-portfolio.netlify.app/"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://sauraevshing-portfolio.netlify.app/"
		)
),
		externalDocs = @ExternalDocumentation(
				description = "RestAPI Documentation",
				url = "https://sauraevshing-portfolio.netlify.app/"
		)
)
public class RestapiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

}

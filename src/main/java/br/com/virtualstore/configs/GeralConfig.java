package br.com.virtualstore.configs;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class GeralConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API to manager application Virtual Store")
                        .version("v1")
                        .description("RESTful API develop with Java 17 and Spring Boot 3.0.2")
                        .termsOfService("https://www.junior.com.br")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://www.junior.com.br")
                        )
                );
    }

}

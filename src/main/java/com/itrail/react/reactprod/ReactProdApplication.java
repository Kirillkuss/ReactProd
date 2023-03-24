package com.itrail.react.reactprod;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://localhost:8081/webjars/swagger-ui/index.html

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API for React", version = "3.0", description = "REACT"))
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class ReactProdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactProdApplication.class, args);
        System.out.println("REACT PRO SUCCESS");
    }

}

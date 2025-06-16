package com.Ahmed.SoltanSalman.global_helpers;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API Documentation")
                        .version("v1.0")
                        .description("This is the API documentation for the deployed application.")
                )
                .servers(List.of(
                        new Server()
                                .url("/")
                                .description("Deployed Server (HTTPS)")
                ));
    }
}

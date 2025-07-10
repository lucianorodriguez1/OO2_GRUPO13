package com.oo2.grupo13.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.oo2.grupo13.Grupo13Application;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {

    private final Grupo13Application grupo13Application;

    SwaggerConfiguration(Grupo13Application grupo13Application) {
        this.grupo13Application = grupo13Application;
    }

    private SecurityScheme createBasicScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .scheme("basic");
    }

    @Bean
    OpenAPI customOpenAPI() {
        OpenAPI api = new OpenAPI();

        api.addSecurityItem(new SecurityRequirement().
            addList("BasicAuth"))
        .components(new Components().addSecuritySchemes
            ("BasicAuth", createBasicScheme()))
        .info(new Info()
                .title("Sistema de Gestión de Tickets")
                .version("1.0.0")
                .description("API REST para gestionar tickets de soporte")
                .license(new License().name("MIT License")));

        return api;
    }
}
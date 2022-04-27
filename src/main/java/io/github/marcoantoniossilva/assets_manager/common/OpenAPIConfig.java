package io.github.marcoantoniossilva.assets_manager.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Assets-Manager"))
public class OpenAPIConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    final String securitySchemeName = "bearerAuth";

    return new OpenAPI()
      .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
      .components(
        new Components()
          .addSecuritySchemes(securitySchemeName,
            new SecurityScheme()
              .name(securitySchemeName)
              .type(SecurityScheme.Type.HTTP)
          )
      );
  }

}
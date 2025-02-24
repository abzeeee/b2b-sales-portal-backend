package org.b2bsalesportal.userservice.config.openApiConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User service API",
                version = "1.0",
                description = "API documentation for the User Service"
        )
)
public class OpenApiConfig {
}

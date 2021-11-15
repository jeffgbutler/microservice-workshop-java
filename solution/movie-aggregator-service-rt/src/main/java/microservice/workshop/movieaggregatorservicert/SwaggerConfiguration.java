package microservice.workshop.movieaggregatorservicert;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Movie Aggregator Service API (REST Template)", version = "v0.0.1"))
public class SwaggerConfiguration {
}

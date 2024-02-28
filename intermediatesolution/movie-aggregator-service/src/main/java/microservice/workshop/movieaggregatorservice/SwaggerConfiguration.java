package microservice.workshop.movieaggregatorservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Movie Aggregator Service API (OpenFeign)", version = "v0.0.1"))
public class SwaggerConfiguration {
}

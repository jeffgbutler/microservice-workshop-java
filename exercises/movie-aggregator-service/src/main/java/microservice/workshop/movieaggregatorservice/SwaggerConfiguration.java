package microservice.workshop.movieaggregatorservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
@Controller
@OpenAPIDefinition(info = @Info(title = "Movie Aggregator Service API (OpenFeign)", version = "v0.0.1"))
public class SwaggerConfiguration {

    @RequestMapping("/")
    public RedirectView redirectToSwagger() {
        return new RedirectView("swagger-ui.html");
    }
}

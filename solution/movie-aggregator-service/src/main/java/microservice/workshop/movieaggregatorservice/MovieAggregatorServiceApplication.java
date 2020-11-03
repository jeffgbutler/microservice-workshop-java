package microservice.workshop.movieaggregatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import microservice.workshop.movieaggregatorservice.service.AggregateMovieService;

@SpringBootApplication
@EnableFeignClients
public class MovieAggregatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieAggregatorServiceApplication.class, args);
    }

    @Bean
    public AggregateMovieService aggregateMovieService() {
        return new AggregateMovieService();
    }
}

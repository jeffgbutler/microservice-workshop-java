package microservice.workshop.movieaggregatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import microservice.workshop.movieaggregatorservice.service.AggregateMovieService;

@SpringBootApplication
/*
 * TODO (Exercise 3) - uncomment this annotation to enable Feign clients
 *
 * @EnableFeignClients
 */
/*
 * TODO (Exercise 5) - uncomment this annotation to turn on Hystrix
 *
 * @EnableCircuitBreaker
 */
public class MovieAggregatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieAggregatorServiceApplication.class, args);
    }

    @Bean
    public AggregateMovieService aggregateMovieService() {
        return new AggregateMovieService();
    }
}

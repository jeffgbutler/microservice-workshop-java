package microservice.workshop.movieaggregatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import microservice.workshop.movieaggregatorservice.service.AggregateMovieService;
import microservice.workshop.movieaggregatorservice.service.fallback.MovieAwardServiceFallback;
import microservice.workshop.movieaggregatorservice.service.fallback.MovieCastServiceFallback;
import microservice.workshop.movieaggregatorservice.service.fallback.MovieServiceFallback;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class MovieAggregatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieAggregatorServiceApplication.class, args);
    }

    @Bean
    public AggregateMovieService aggregateMovieService() {
        return new AggregateMovieService();
    }

    @Bean
    public MovieAwardServiceFallback movieAwardServiceFallback() {
        return new MovieAwardServiceFallback();
    }
    
    @Bean
    public MovieServiceFallback movieServiceFallback() {
        return new MovieServiceFallback();
    }
    
    @Bean
    public MovieCastServiceFallback movieCastServiceFallback() {
        return new MovieCastServiceFallback();
    }
}

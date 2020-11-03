package microservice.workshop.movieaggregatorservice.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import microservice.workshop.movieaggregatorservice.model.Movie;

@FeignClient(name="movie-service", decode404=true)
public interface MovieService {

    @GetMapping("/movie/{id}")
    Optional<Movie> findById(@PathVariable("id") Integer id);
}

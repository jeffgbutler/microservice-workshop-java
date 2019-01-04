package microservice.workshop.movieaggregatorservice.service;

import java.util.Optional;

import microservice.workshop.movieaggregatorservice.model.Movie;

/*
 * TODO (lab 2) - create Feign clients for the three individual services, then
 * use the Feign clients in this service to compose a complete object. Call the movie-service first.
 * If the movie service returns a value, then call the cast and award services to compose the full
 * movie object.
 */
public class AggregateMovieService {
    
    public Optional<Movie> findById(Integer id) {
        return Optional.empty();
    }
}

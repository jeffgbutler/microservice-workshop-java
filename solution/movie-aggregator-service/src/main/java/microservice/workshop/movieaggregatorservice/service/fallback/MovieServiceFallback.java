package microservice.workshop.movieaggregatorservice.service.fallback;

import java.util.Optional;

import microservice.workshop.movieaggregatorservice.model.Movie;
import microservice.workshop.movieaggregatorservice.service.MovieService;

public class MovieServiceFallback implements MovieService {

    @Override
    public Optional<Movie> findById(Integer id) {
        return Optional.empty();
    }
}

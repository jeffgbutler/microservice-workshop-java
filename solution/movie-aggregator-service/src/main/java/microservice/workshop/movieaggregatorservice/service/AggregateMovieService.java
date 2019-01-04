package microservice.workshop.movieaggregatorservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import microservice.workshop.movieaggregatorservice.model.Movie;

public class AggregateMovieService {
    
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieAwardService awardService;
    @Autowired
    private MovieCastService castService;

    public Optional<Movie> findById(Integer id) {
        return movieService.findById(id)
                .flatMap(this::augmentMovie);
    }
    
    private Optional<Movie> augmentMovie(Movie movie) {
        movie.addAwards(awardService.findAwardsForMovie(movie.getId()));
        movie.addCastMembers(castService.findCastMembers(movie.getId()));
        return Optional.of(movie);
    }
}

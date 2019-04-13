package microservice.workshop.movieaggregatorservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import microservice.workshop.movieaggregatorservice.model.Movie;

public class AggregateMovieService {

    @Autowired
    private MovieAwardService movieAwardService;
    @Autowired
    private MovieCastService movieCastService;
    @Autowired
    private MovieService movieService;

    public Optional<Movie> findById(Integer id) {
        return movieService.findById(id).map(this::augmentMovie);
    }

    private Movie augmentMovie(Movie movie) {
        movie.addAwards(movieAwardService.findAwardsForMovie(movie.getId()));
        movie.addCastMembers(movieCastService.findCastMembers(movie.getId()));
        return movie;
    }
}

package microservice.workshop.movieaggregatorservicert.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import microservice.workshop.movieaggregatorservicert.model.Movie;

@Service
public class AggregateMovieService {
    
    private final MovieService movieService;
    private final MovieAwardService awardService;
    private final MovieCastService castService;

    public AggregateMovieService(MovieService movieService, MovieAwardService awardService, MovieCastService castService) {
        this.movieService = movieService;
        this.awardService = awardService;
        this.castService = castService;
    }

    public Optional<Movie> findById(Integer id) {
        return movieService.findById(id)
                .map(this::augmentMovie);
    }
    
    private Movie augmentMovie(Movie movie) {
        movie.addAwards(awardService.findAwardsForMovie(movie.getId()));
        movie.addCastMembers(castService.findCastMembers(movie.getId()));
        return movie;
    }
}

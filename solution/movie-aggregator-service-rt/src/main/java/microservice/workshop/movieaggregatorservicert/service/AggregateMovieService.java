package microservice.workshop.movieaggregatorservicert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservice.workshop.movieaggregatorservicert.model.Movie;

@Service
public class AggregateMovieService {
    
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieAwardService awardService;
    @Autowired
    private MovieCastService castService;

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

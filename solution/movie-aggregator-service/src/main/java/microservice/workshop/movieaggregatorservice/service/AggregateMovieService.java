package microservice.workshop.movieaggregatorservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import microservice.workshop.movieaggregatorservice.model.CastMember;
import microservice.workshop.movieaggregatorservice.model.Movie;
import microservice.workshop.movieaggregatorservice.model.MovieAward;

public class AggregateMovieService {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieAwardService awardService;
    @Autowired
    private MovieCastService castService;
    @Autowired
    private CircuitBreakerFactory<?, ?> cbFactory;

    public Optional<Movie> findById(Integer id) {
        return findMovie(id).map(this::augmentMovie);
    }

    private Movie augmentMovie(Movie movie) {
        movie.addAwards(findAwards(movie.getId()));
        movie.addCastMembers(findCastMembers(movie.getId()));
        return movie;
    }

    private Optional<Movie> findMovie(Integer id) {
        return cbFactory.create("movie-service-cb").run(
                () -> movieService.findById(id),
                this::emptyOptionalFallback
                );
    }
    
    private List<MovieAward> findAwards(Integer id) {
        return cbFactory.create("movie-award-service-cb").run(
                () -> awardService.findAwardsForMovie(id),
                this::emptyListFallback
                );
    }

    private List<CastMember> findCastMembers(Integer id) {
        return cbFactory.create("movie-cast-service-cb").run(
                () -> castService.findCastMembers(id),
                this::emptyListFallback
                );
    }
    
    private <T> List<T> emptyListFallback(Throwable t) {
        return Collections.emptyList();
    }
    
    private <T> Optional<T> emptyOptionalFallback(Throwable t) {
        return Optional.empty();
    }
}

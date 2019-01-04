package microservice.workshop.movieaggregatorservice.service.fallback;

import java.util.Collections;
import java.util.List;

import microservice.workshop.movieaggregatorservice.model.MovieAward;
import microservice.workshop.movieaggregatorservice.service.MovieAwardService;

public class MovieAwardServiceFallback implements MovieAwardService {

    @Override
    public List<MovieAward> findAwardsForMovie(Integer movieId) {
        return Collections.emptyList();
    }
}

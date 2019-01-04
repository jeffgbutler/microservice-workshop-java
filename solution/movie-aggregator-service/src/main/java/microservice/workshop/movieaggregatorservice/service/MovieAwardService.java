package microservice.workshop.movieaggregatorservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import microservice.workshop.movieaggregatorservice.model.MovieAward;
import microservice.workshop.movieaggregatorservice.service.fallback.MovieAwardServiceFallback;

@FeignClient(name="movie-award-service", fallback=MovieAwardServiceFallback.class)
public interface MovieAwardService {

    @GetMapping("/award/search")
    List<MovieAward> findAwardsForMovie(@RequestParam("movieId") Integer movieId);
}

package microservice.workshop.movieaggregatorservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import microservice.workshop.movieaggregatorservice.model.CastMember;

@FeignClient(name="movie-cast-service")
public interface MovieCastService {

    @GetMapping("/cast/search")
    List<CastMember> findCastMembers(@RequestParam("movieId") Integer movieId);
}

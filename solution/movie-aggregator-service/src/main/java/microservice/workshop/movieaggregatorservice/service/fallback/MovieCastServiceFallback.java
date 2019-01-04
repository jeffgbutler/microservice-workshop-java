package microservice.workshop.movieaggregatorservice.service.fallback;

import java.util.Collections;
import java.util.List;

import microservice.workshop.movieaggregatorservice.model.CastMember;
import microservice.workshop.movieaggregatorservice.service.MovieCastService;

public class MovieCastServiceFallback implements MovieCastService {

    @Override
    public List<CastMember> findCastMembers(Integer movieId) {
        return Collections.emptyList();
    }
}

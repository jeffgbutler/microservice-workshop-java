package microservice.workshop.movieawardservice.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.workshop.movieawardservice.model.Award;

public interface AwardRepository extends JpaRepository<Award, Integer> {
    List<Award> findByMovieId(Integer movieId);
}

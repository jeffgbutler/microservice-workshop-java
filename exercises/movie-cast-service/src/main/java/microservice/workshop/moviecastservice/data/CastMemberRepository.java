package microservice.workshop.moviecastservice.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.workshop.moviecastservice.model.CastMember;

public interface CastMemberRepository extends JpaRepository<CastMember, Integer> {
    List<CastMember> findByMovieId(Integer movieId);
}

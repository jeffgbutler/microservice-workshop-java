package microservice.workshop.movieservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import microservice.workshop.movieservice.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}

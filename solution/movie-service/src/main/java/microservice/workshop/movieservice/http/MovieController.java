package microservice.workshop.movieservice.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.workshop.movieservice.data.MovieRepository;
import microservice.workshop.movieservice.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.of(movieRepository.findById(id));
    }
}

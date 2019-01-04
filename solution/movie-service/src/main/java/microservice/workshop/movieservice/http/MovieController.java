package microservice.workshop.movieservice.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private MovieRepository movieRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Integer id) {
        return movieRepository.findById(id)
                .map(this::found)
                .orElseGet(this::notFound);
    }
    
    private ResponseEntity<Movie> found(Movie movie) {
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    private ResponseEntity<Movie> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

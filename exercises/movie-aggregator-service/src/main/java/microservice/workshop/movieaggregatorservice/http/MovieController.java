package microservice.workshop.movieaggregatorservice.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.workshop.movieaggregatorservice.model.Movie;
import microservice.workshop.movieaggregatorservice.service.AggregateMovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private AggregateMovieService service;
    
    @GetMapping("/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Movie> getById(@PathVariable("id") Integer id) {
        return service.findById(id)
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

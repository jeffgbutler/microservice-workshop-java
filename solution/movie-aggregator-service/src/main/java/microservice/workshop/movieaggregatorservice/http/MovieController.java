package microservice.workshop.movieaggregatorservice.http;

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

    private final AggregateMovieService service;

    public MovieController(AggregateMovieService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Movie> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.of(service.findById(id));
    }
}

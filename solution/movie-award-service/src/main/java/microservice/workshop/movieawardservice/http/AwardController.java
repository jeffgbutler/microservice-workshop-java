package microservice.workshop.movieawardservice.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservice.workshop.movieawardservice.data.AwardRepository;
import microservice.workshop.movieawardservice.model.Award;

@RestController
@RequestMapping("/award")
public class AwardController {

    private final AwardRepository repository;

    public AwardController(AwardRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Award>> search(@RequestParam("movieId") Integer movieId) {
        return ResponseEntity.ok(repository.findByMovieId(movieId));
    }
}

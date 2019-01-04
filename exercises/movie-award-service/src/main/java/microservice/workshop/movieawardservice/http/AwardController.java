package microservice.workshop.movieawardservice.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private AwardRepository repository;
    
    @GetMapping("/search")
    ResponseEntity<List<Award>> search(@RequestParam("movieId") Integer movieId) {
        List<Award> answer = repository.findByMovieId(movieId);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}

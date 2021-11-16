package microservice.workshop.moviecastservice.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservice.workshop.moviecastservice.data.CastMemberRepository;
import microservice.workshop.moviecastservice.model.CastMember;

@RestController
@RequestMapping("/cast")
public class CastController {

    private final CastMemberRepository repository;

    public CastController(CastMemberRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CastMember>> search(@RequestParam("movieId") Integer movieId) {
        return ResponseEntity.ok(repository.findByMovieId(movieId));
    }
}

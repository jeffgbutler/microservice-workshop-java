package microservice.workshop.movieaggregatorservicert.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import microservice.workshop.movieaggregatorservicert.model.Movie;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private CircuitBreakerFactory<?, ?> cbFactory;

    public Optional<Movie> findById(Integer id) {
        return cbFactory.create("movie-service-cb").run(
                () -> getRemoteMovie(id),
                t -> Optional.empty());
    }
    
    private Optional<Movie> getRemoteMovie(Integer movieId) {
        String url = discoveryClient.getInstances("movie-service").stream()
                .findFirst()
                .map(ServiceInstance::getUri)
                .map(URI::toString)
                .map(s -> buildFullUrl(s, movieId))
                .orElseThrow(() -> new IllegalStateException("movie-service not available"));
        
        return Optional.ofNullable(restTemplate.getForObject(url, Movie.class));
        
    }
    
    private String buildFullUrl(String url, Integer movieId) {
        return UriComponentsBuilder.fromHttpUrl(url)
        .pathSegment("movie", movieId.toString())
        .toUriString();
    }
}

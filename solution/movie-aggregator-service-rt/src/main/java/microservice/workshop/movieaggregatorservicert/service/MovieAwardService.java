package microservice.workshop.movieaggregatorservicert.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import microservice.workshop.movieaggregatorservicert.model.MovieAward;

@Service
public class MovieAwardService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final CircuitBreakerFactory<?, ?> cbFactory;

    public MovieAwardService(RestTemplate restTemplate, DiscoveryClient discoveryClient, CircuitBreakerFactory<?,?> cbFactory) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.cbFactory = cbFactory;
    }

    public List<MovieAward> findAwardsForMovie(Integer movieId) {
        return cbFactory.create("movie-award-service-cb").run(
                () -> getRemoteAwards(movieId),
                t -> Collections.emptyList());
    }
    
    private List<MovieAward> getRemoteAwards(Integer movieId) {
        String url = discoveryClient.getInstances("movie-award-service").stream()
                .findFirst()
                .map(ServiceInstance::getUri)
                .map(URI::toString)
                .map(s -> buildFullUrl(s, movieId))
                .orElseThrow(() -> new IllegalStateException("movie-award-service not available"));
        
        ResponseEntity<List<MovieAward>> ent =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return ent.getBody();
        
    }
    
    private String buildFullUrl(String url, Integer movieId) {
        return UriComponentsBuilder.fromHttpUrl(url)
        .pathSegment("award","search")
        .queryParam("movieId", movieId)
        .toUriString();
    }
}

package microservice.workshop.movieaggregatorservicert.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import microservice.workshop.movieaggregatorservicert.model.CastMember;

@Service
public class MovieCastService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private CircuitBreakerFactory<?, ?> cbFactory;

    public List<CastMember> findCastMembers(Integer movieId) {
        return cbFactory.create("movie-cast-service-cb").run(
                () -> getRemoteCastMembers(movieId),
                t -> Collections.emptyList());
    }

    private List<CastMember> getRemoteCastMembers(Integer movieId) {
        String url = discoveryClient.getInstances("movie-cast-service").stream()
                .findFirst()
                .map(ServiceInstance::getUri)
                .map(URI::toString)
                .map(s -> buildFullUrl(s, movieId))
                .orElseThrow(() -> new IllegalStateException("movie-cast-service not available"));
        
        ResponseEntity<List<CastMember>> ent =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CastMember>>() {});
        return ent.getBody();
        
    }
    
    private String buildFullUrl(String url, Integer movieId) {
        return UriComponentsBuilder.fromHttpUrl(url)
        .pathSegment("cast","search")
        .queryParam("movieId", movieId)
        .toUriString();
    }
}

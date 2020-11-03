package microservice.workshop.movieaggregatorservicert.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private Integer id;
    private String title;
    private LocalDate releaseDate;
    private int runLength;
    private List<MovieAward> awards = new ArrayList<>();
    private List<CastMember> castMembers = new ArrayList<>();
    
    public List<MovieAward> getAwards() {
        return awards;
    }
    
    public void addAwards(List<MovieAward> awards) {
        this.awards.addAll(awards);
    }
    
    public List<CastMember> getCastMembers() {
        return castMembers;
    }
    
    public void addCastMembers(List<CastMember> castMembers) {
        this.castMembers.addAll(castMembers);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunLength() {
        return runLength;
    }

    public void setRunLength(int runLength) {
        this.runLength = runLength;
    }
}

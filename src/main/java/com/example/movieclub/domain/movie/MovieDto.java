package com.example.movieclub.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Getter
public class MovieDto implements Serializable {
    private final Long id;
    private final String title;
    private final String originalTitle;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private final Integer releaseYear;
    private String genre;
    private final boolean promoted;
    private String poster;
}

package com.example.movieclub.domain.movie;

import com.example.movieclub.domain.genre.Genre;
import com.example.movieclub.domain.rating.Rating;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;


public class MovieMapper {
    public static MovieDto map(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getDescription(),
                movie.getShortDescription(),
                movie.getYoutubeTrailerId(),
                movie.getReleaseYear(),
                movie.getGenre().getName(),
                movie.isPromoted(),
                movie.getPoster(),
                movie.getRatings().size(),
                movie.getRatings().stream()
                        .map(Rating::getRating).mapToDouble(e->e).average().orElse(0));
    }

}

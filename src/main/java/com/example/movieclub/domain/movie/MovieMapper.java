package com.example.movieclub.domain.movie;

import com.example.movieclub.domain.rating.Rating;

public class MovieMapper {

    private static final String URL="/film/%d";
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
                        .map(Rating::getRating).mapToDouble(e -> e).average().orElse(0));
    }

    public static MovieProfileDto mapToProfile(Movie movie) {
        MovieProfileDto movieDto = new MovieProfileDto();
        movieDto.setTitle(movie.getTitle());
        String url=String.format(URL,movie.getId());
        movieDto.setUrl(url);
        return movieDto;
    }
}

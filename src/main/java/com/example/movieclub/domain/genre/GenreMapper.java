package com.example.movieclub.domain.genre;

public class GenreMapper {

    public static GenreDto map(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }
}

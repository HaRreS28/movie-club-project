package com.example.movieclub.domain.genre;

public class GenreMapper {

    public static GenreDto map(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }

    public static Genre map(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre.setDescription(genreDto.getDescription());
        return genre;
    }
}

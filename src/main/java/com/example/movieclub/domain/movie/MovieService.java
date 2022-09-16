package com.example.movieclub.domain.movie;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MovieDto> findAllPromotedMovies() {
        return movieRepository.findAllByPromotedIsTrue()
                .stream()
                .map(MovieMapper::map)
                .toList();
    }

    public Optional<MovieDto> findById(Long id) {
        return movieRepository.findById(id).map(MovieMapper::map);
    }

    public List<MovieDto> findAllByGenreName(String name) {
        return movieRepository.findAllByGenre_NameIgnoreCase(name)
                .stream().map(MovieMapper::map).toList();
    }
}

package com.example.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Optional<GenreDto> findByName(String name){
        return genreRepository.findByNameIgnoreCase(name).map(GenreMapper::map);
    }
}

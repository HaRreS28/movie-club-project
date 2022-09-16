package com.example.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Optional<GenreDto> findByName(String name) {
        return genreRepository.findByNameIgnoreCase(name).map(GenreMapper::map);
    }

    public List<GenreDto> findAll() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreMapper::map).toList();
    }

    @Transactional
    public GenreDto addGenre(GenreDto genreDto){
        Genre genre = GenreMapper.map(genreDto);
        return GenreMapper.map(genreRepository.save(genre));
    }
}

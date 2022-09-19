package com.example.movieclub.domain.movie;

import com.example.movieclub.domain.genre.Genre;
import com.example.movieclub.domain.genre.GenreRepository;
import com.example.movieclub.domain.storage.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FileStorageService fileStorageService;

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

    @Transactional
    public MovieDto addMovie(MovieSaveDto movieSaveDto){
        Movie movie = new Movie();
        movie.setDescription(movieSaveDto.getDescription());
        movie.setPromoted(movieSaveDto.isPromoted());
        movie.setOriginalTitle(movieSaveDto.getOriginalTitle());
        movie.setReleaseYear(movieSaveDto.getReleaseYear());
        movie.setTitle(movieSaveDto.getTitle());
        movie.setYoutubeTrailerId(movieSaveDto.getYoutubeTrailerId());
        movie.setShortDescription(movieSaveDto.getShortDescription());

        Genre genre = genreRepository.findByNameIgnoreCase(movieSaveDto.getGenre()).orElseThrow();
        movie.setGenre(genre);

        if(movieSaveDto.getPoster().getSize()>2) {
            String saveImage = fileStorageService.saveImage(movieSaveDto.getPoster());
            movie.setPoster(saveImage);
        }
        movieRepository.save(movie);
        return MovieMapper.map(movie);

    }
}

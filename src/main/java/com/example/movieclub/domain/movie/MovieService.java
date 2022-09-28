package com.example.movieclub.domain.movie;

import com.example.movieclub.domain.genre.Genre;
import com.example.movieclub.domain.genre.GenreRepository;
import com.example.movieclub.domain.storage.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
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

    public List<MovieDto> findAllByGenreName(String name,int pageNumber,int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return movieRepository.findAllByGenre_NameIgnoreCase(name,pageable)
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

    public List<MovieDto> findTopMovies(int size){
        Pageable pageable = Pageable.ofSize(size);
        return movieRepository.findTopByRating(pageable)
                .stream()
                .map(MovieMapper::map)
                .toList();
    }

    public List<MovieDto> getMovies(String title){
        return movieRepository.findMovies(title.toUpperCase())
                .stream()
                .map(MovieMapper::map)
                .toList();
    }

    public Page<MovieDto> getPage(int pageNumber,int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return movieRepository.findAll(pageable).map(MovieMapper::map);
    }
}

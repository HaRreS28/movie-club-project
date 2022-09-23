package com.example.movieclub.domain.rating;

import com.example.movieclub.domain.movie.Movie;
import com.example.movieclub.domain.movie.MovieRepository;
import com.example.movieclub.domain.user.AppUser;
import com.example.movieclub.domain.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final AppUserRepository userRepository;
    private final MovieRepository movieRepository;

    public void addOrUpdateRating(String userEmail, long movieId, int rating) {
        Rating ratingToSaveOrUpdate = ratingRepository.findByAppUser_EmailAndMovie_Id(userEmail, movieId)
                .orElseGet(Rating::new);
        AppUser user = userRepository.findByEmail(userEmail).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        ratingToSaveOrUpdate.setAppUser(user);
        ratingToSaveOrUpdate.setMovie(movie);
        ratingToSaveOrUpdate.setRating(rating);
        ratingRepository.save(ratingToSaveOrUpdate);
    }


    public Optional<Integer> getUserRatingForMovie(String email,Long movieId){
      return  ratingRepository.findByAppUser_EmailAndMovie_Id(email,movieId).map(Rating::getRating);
    }



}

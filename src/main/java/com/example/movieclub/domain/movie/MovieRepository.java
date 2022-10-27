package com.example.movieclub.domain.movie;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends PagingAndSortingRepository<Movie,Long> {
    List<Movie> findAllByPromotedIsTrue();
    List<Movie> findAllByGenre_NameIgnoreCase(String name,Pageable pageable);

    @Query("Select count(m.id) from Movie m where m.genre.name=:name")
     int getPageSizeGenre(String name);

    Set<Movie> findAllByRatings_AppUser_Email(String email);
    Set<Movie> findAllByComments_AppUser_Email(String email);

    @Query("SELECT m from Movie m join m.ratings r group by m order by avg(r.rating) desc")
    List<Movie> findTopByRating(Pageable pageable);

    @Query("SELECT m from Movie m  where UPPER(m.title) like %:title%")
    List<Movie> findMovies(String title);

    @Query("Select Count(m) from Movie m")
    int getQuantityOfMovies();
}

package com.example.movieclub.domain.rating;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating,Long> {
    Optional<Rating> findByAppUser_EmailAndMovie_Id(String email,Long id);
}

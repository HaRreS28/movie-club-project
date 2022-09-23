package com.example.movieclub.domain.user.token;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token,Long> {
    Optional<Token> findByToken(String token);
    void deleteByAppUser_Email(String email);

    boolean existsByToken(String token);

    boolean existsByToken(String token);
}

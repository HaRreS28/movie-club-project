package com.example.movieclub.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
}

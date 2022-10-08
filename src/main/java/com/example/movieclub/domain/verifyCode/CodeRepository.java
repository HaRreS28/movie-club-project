package com.example.movieclub.domain.verifyCode;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code,Long> {
    boolean existsByCode(String code);
    void deleteAllByAppUser_Email(String email);
    Optional<Code> findByCode(String code);
}

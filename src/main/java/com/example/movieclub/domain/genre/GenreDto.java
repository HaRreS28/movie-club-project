package com.example.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class GenreDto {
    private final Long id;
    private final String name;
    private final String description;
}

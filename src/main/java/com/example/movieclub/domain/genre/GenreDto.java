package com.example.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GenreDto {
    private Long id;
    private String name;
    private String description;
}

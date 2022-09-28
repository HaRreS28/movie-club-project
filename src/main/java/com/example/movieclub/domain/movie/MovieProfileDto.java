package com.example.movieclub.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MovieProfileDto {
    private String title;
    private String url;
    private Integer rate;
    private int numberOfComments;
}

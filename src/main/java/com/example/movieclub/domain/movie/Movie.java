package com.example.movieclub.domain.movie;

import com.example.movieclub.domain.genre.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "original_title")
    private String originalTitle;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "description")
    private String description;
    @Column(name = "youtube_trailer_id")
    private String youtubeTrailerId;
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id", name = "genre_id")
    private Genre genre;
    @Column(name = "promoted")
    private boolean promoted;
    private String poster;

}

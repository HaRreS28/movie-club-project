package com.example.movieclub.domain.movie;

import com.example.movieclub.domain.comment.Comment;
import com.example.movieclub.domain.genre.Genre;
import com.example.movieclub.domain.rating.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @OneToMany(mappedBy = "movie")
    private Set<Rating> ratings = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments=new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

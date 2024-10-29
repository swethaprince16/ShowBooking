package com.showbooking.models;


import com.showbooking.dto.MovieDto;
import com.showbooking.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Movie  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "author name should not be blank")
    private String movieTitle;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Double rating;
    @OneToMany(mappedBy = "movie" )
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows;

    public static MovieDto toDto(Movie movie){
        return MovieDto.builder()
                .id(movie.getId())
                .genre(movie.getGenre())
                .movieName(movie.getMovieTitle())
                .rating(movie.getRating())
                .reviews(Review.toReviewDtoList(movie.getReviews()))
                .build();
    }

}

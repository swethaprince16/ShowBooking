package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.showbooking.dto.MovieDto;
import com.showbooking.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private String movieReview;
    private Double rating;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnore
    private Movie movie;

    @CreationTimestamp
    LocalDateTime createdTime;
    @CreationTimestamp
    LocalDateTime updatedTime;

    public static ReviewDto toDto(Review review){
        return ReviewDto.builder()
                .movieReview(review.getMovieReview())
                .rating(review.getRating())
                .movieId(review.getMovie().getId())
                .build();
    }

    public static List<ReviewDto> toReviewDtoList(List<Review> reviews){
        if(reviews==null)
            return new ArrayList<>();
        return reviews.stream().map(Review::toDto).toList();
    }

    public static Review toEntity(ReviewDto reviewDto){
        return Review.builder()
                .movieReview(reviewDto.getMovieReview())
                .rating(reviewDto.getRating())
                .movie(Movie.builder().id(reviewDto.getMovieId()).build())
                .build();
    }
}

package com.showbooking.service;

import com.showbooking.dto.ReviewDto;
import com.showbooking.exception.DataNotFoundException;
import com.showbooking.models.Movie;
import com.showbooking.models.Review;
import com.showbooking.repository.MovieRepository;
import com.showbooking.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;

    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = new Review();
        Movie movie = movieRepository.findById(reviewDto.getMovieId()).orElseThrow(
                ()-> new DataNotFoundException("no data found")
        );
            review.setMovieReview(reviewDto.getMovieReview());
            review.setRating(reviewDto.getRating());
            review.setMovie(movie);
            reviewRepository.save(review);

            Double average = reviewRepository.getReviewAverage(movie.getId());
            movie.setRating(average);
            movieRepository.save(movie);


        return Review.toDto(review);

    }

    public ReviewDto getReviewById(Integer id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(" No review for id mentioned")
        );

        return Review.toDto(review);
    }
}

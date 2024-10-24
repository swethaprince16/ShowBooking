package com.showbooking;

import com.showbooking.dto.ReviewDto;
import com.showbooking.models.Movie;
import com.showbooking.models.Review;
import com.showbooking.repository.MovieRepository;
import com.showbooking.repository.ReviewRepository;
import com.showbooking.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    ReviewService reviewService;

    @Mock
    ReviewRepository reviewRepository;
    @Mock
    MovieRepository movieRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddReview(){
        Review review = Review.builder()
                .movie(Movie.builder().id(1).build())
                .reviewId(1)
                .movieReview("nice")
                .rating(3.0)
                .build();

        ReviewDto reviewdto = ReviewDto.builder()
                .movieReview("nice")
                .movieId(1)
                .rating(3.0)
                .build();
        Movie movie = Movie.builder()
                .id(1)
                .rating(4.0)
                .movieTitle("sky")
                .build();

        when(reviewRepository.save(any())).thenReturn(review);
        when(movieRepository.findById(any())).thenReturn(Optional.ofNullable(movie));
        when(reviewRepository.getReviewAverage(any())).thenReturn(3.0);

        ReviewDto result = reviewService.addReview(reviewdto);

        assertNotNull(result);
        assertEquals(result.getMovieReview(), reviewdto.getMovieReview());

        verify(movieRepository).save(any());
        verify(reviewRepository).save(any());
        verify(reviewRepository).getReviewAverage(any());
    }
}

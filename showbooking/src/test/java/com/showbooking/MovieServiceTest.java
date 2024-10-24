package com.showbooking;

import com.showbooking.dto.MovieDto;
import com.showbooking.enums.Genre;
import com.showbooking.exception.DataNotFoundException;
import com.showbooking.models.Movie;
import com.showbooking.repository.MovieRepository;
import com.showbooking.service.MovieService;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindMovieByTitleExist(){
        String title = "sky";
        Movie movie = Movie.builder()
                .id(1)
                .movieTitle("sky")
                .genre(Genre.valueOf("COMEDY"))
                .rating(3.0)
                .reviews(new ArrayList<>())
                .build();

        when(movieRepository.findByMovieTitle(title)).thenReturn(movie);

        MovieDto result = movieService.findByTitle(title);

        assertNotNull(result);
        assertEquals(title, result.getMovieName());
        verify(movieRepository).findByMovieTitle(title);
    }

    @Test
    public void testFindMovieByTitleNotExist(){
        String title = "sky";
        String message = "no movie found for the title";

        when(movieRepository.findByMovieTitle(title)).thenReturn(null);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                ()->movieService.findByTitle(title));

        assertEquals(message, exception.getMessage());
        verify(movieRepository).findByMovieTitle(title);
    }
}

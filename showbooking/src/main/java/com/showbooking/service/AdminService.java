package com.showbooking.service;

import com.showbooking.dto.MovieDto;
import com.showbooking.models.Movie;
import com.showbooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private MovieRepository movieRepository;

    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setMovieTitle(movieDto.getMovieName());
        movie.setGenre(movieDto.getGenre());
        movie = movieRepository.save(movie);
        return Movie.toDto(movie);
    }
}

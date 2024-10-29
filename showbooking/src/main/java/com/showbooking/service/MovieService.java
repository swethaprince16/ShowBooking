package com.showbooking.service;

import com.showbooking.dto.MovieDto;
import com.showbooking.enums.Genre;
import com.showbooking.exception.DataNotFoundException;
import com.showbooking.models.Movie;
import com.showbooking.repository.MovieRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setMovieTitle(movieDto.getMovieName());
        movie.setGenre(movieDto.getGenre());
        movie = movieRepository.save(movie);
        return Movie.toDto(movie);
    }

    public List<MovieDto> findMovieByGenre(String genre) {
        try{
            //        List<Movie> movies = movieRepository.findMovieByGenre(Genre.valueOf(genre));
            List<Movie> movies = movieRepository.findMovieByGenre(genre);
            if(movies.isEmpty()){
                throw new DataNotFoundException("no movies found for the genre");
            }

            List<MovieDto> movieDtoList = movies.stream().map(Movie::toDto).toList();
            if(movieDtoList.size()>5)
                return movieDtoList.subList(0,4);
            return movieDtoList;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    public MovieDto findByTitle(String title) {
        Movie movie = movieRepository.findByMovieTitle(title);
        if(movie==null){
            throw new DataNotFoundException("no movie found for the title");
        }
        return  Movie.toDto(movie);
    }
}

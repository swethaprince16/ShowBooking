package com.showbooking.controller;

import com.showbooking.dto.MovieDto;
import com.showbooking.models.Movie;
import com.showbooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);

    }

    @GetMapping("/movieByGenre/{genre}")
    public ResponseEntity<List<MovieDto>> findMovieByGenre(@PathVariable String genre){
        return new ResponseEntity<>(movieService.findMovieByGenre(genre), HttpStatus.OK);

    }

    @GetMapping("/title/{title}")
    public ResponseEntity<MovieDto> findByTitle(@PathVariable String title){
        return new ResponseEntity<>(movieService.findByTitle(title), HttpStatus.OK);

    }
}

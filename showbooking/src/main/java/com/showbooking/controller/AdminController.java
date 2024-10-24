package com.showbooking.controller;

import com.showbooking.dto.MovieDto;
import com.showbooking.service.AdminService;
import com.showbooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie){
        return new ResponseEntity<>(adminService.addMovie(movie), HttpStatus.CREATED);

    }

}

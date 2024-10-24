package com.showbooking.controller;

import com.showbooking.dto.TheaterDto;
import com.showbooking.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    @PostMapping
    public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto theaterDto){
        return new ResponseEntity<>(theaterService.addTheater(theaterDto), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable Integer id){
        return new ResponseEntity<>(theaterService.getTheaterById(id), HttpStatus.OK);
    }
}

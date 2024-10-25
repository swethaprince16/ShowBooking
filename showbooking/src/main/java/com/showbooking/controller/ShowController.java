package com.showbooking.controller;

import com.showbooking.dto.ShowDto;
import com.showbooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<ShowDto> addShow(@RequestBody ShowDto showDto){
        return new ResponseEntity<>(showService.addShow(showDto), HttpStatus.CREATED);

    }

    @GetMapping("/search")
    public ResponseEntity<List<ShowDto>> searchShows(@RequestParam(name = "city", required = true) String city,
                                                     @RequestParam(name = "theaterName", required = true) String theaterName,
                                                     @RequestParam(name = "movie", required = false) String movie){
        return new ResponseEntity<>(showService.searchShows(city,theaterName,movie), HttpStatus.OK);

    }
}

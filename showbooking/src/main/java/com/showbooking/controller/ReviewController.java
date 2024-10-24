package com.showbooking.controller;

import com.showbooking.dto.ReviewDto;
import com.showbooking.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.addReview(reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Integer id){
        return new ResponseEntity<>(reviewService.getReviewById(id),HttpStatus.OK);
    }
}

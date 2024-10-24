package com.showbooking.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String movieReview;
    private Double rating;
    private Integer movieId;
}

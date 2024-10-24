package com.showbooking.dto;

import com.showbooking.enums.Genre;
import com.showbooking.models.Review;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Integer id;
    private String movieName;
    private Genre genre;
    private Double rating;
    private List<ReviewDto> reviews;
}

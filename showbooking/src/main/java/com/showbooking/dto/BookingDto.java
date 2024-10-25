package com.showbooking.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Integer userId;
    private Integer showId;
    private Set<String> seatNumbers;
    private String seatType;
}

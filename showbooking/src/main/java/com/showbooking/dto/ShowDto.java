package com.showbooking.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowDto {
    private Integer id;
    private LocalDateTime showTime;
    private Integer movieId;
    private Integer theaterId;
}

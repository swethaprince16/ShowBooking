package com.showbooking.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowDto implements Serializable {
    private Integer id;
    private LocalDateTime showTime;
    private Integer movieId;
    private Integer theaterId;
}

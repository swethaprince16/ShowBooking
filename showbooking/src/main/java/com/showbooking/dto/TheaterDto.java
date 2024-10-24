package com.showbooking.dto;

import com.showbooking.models.TheaterSeat;
import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheaterDto {

    private Integer id;
    private String theaterName;
    private String city;
    private List<TheaterSeat> seats;

}

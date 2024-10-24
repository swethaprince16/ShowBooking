package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.showbooking.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    private String seatNumber;
    private Integer price;

    @ManyToOne
    @JsonIgnore
    private Theater theater;
}

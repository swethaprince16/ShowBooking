package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.showbooking.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;
    private SeatType type;
    private String number;
    private Integer rate;
    private boolean booked;

    @ManyToOne
    private Show show;

    @ManyToOne
    @JsonIgnore
    private Ticket ticket;

    @CreationTimestamp
    private LocalDateTime bookedAt;
}

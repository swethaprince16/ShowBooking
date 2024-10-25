package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.showbooking.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

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
    @Enumerated(EnumType.STRING)
    private SeatType type;
    private String number;
    private Integer rate;
    private boolean booked;

    @ManyToOne
    @JsonIgnore
    private Show show;

    @ManyToOne
    @JsonIgnore
    private Ticket ticket;

    @CreationTimestamp
    private Date bookedAt;
}

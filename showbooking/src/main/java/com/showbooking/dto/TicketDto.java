package com.showbooking.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private Integer id;
    private Integer userId;
    private Double amount;
    private String allottedSeats;
    private Date bookedAt;
    private ShowDto showDto;
}

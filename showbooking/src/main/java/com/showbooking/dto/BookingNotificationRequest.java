package com.showbooking.dto;

import com.showbooking.models.Ticket;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingNotificationRequest {
    private Integer userId;
    private String username;
    private String email;
    private Ticket ticket;
}

package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.showbooking.dto.ShowDto;
import com.showbooking.dto.TicketDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private String allocatedSeats;

    @CreationTimestamp
    private Date bookedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JsonIgnore
    private Show show;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnore
    private List<ShowSeat> showSeats;

    public static TicketDto toDto(Ticket ticket){
        return TicketDto.builder()
                .id(ticket.getId())
                .userId(ticket.getUser().getId())
                .amount(ticket.getAmount())
                .bookedAt(ticket.getBookedAt())
                .showDto(Show.toDto(ticket.getShow()))
                .allottedSeats(ticket.getAllocatedSeats())
                .build();
    }


}

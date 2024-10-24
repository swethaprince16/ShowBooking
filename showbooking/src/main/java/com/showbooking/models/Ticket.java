package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    @CreationTimestamp
    private Date bookedAt;

    @ManyToOne
    @JsonIgnore
    private Users user;

    @ManyToOne
    @JsonIgnore
    private Show show;

    @OneToMany(mappedBy = "ticket")
    private List<ShowSeat> showSeats;


}

package com.showbooking.models;

import com.showbooking.dto.TheaterDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theatreId;

    private String theatreName;
    private String city;

    @OneToMany(mappedBy = "theater")
    private List<Show> shows;

    @OneToMany(mappedBy = "theater")
    private List<TheaterSeat> theatreSeats;

    public static TheaterDto toDto(Theater theater){
        return TheaterDto.builder()
                .id(theater.getTheatreId())
                .city(theater.getCity())
                .theaterName(theater.getTheatreName())
                .seats(theater.getTheatreSeats())
                .build();
    }
}
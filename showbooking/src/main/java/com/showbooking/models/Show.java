package com.showbooking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.showbooking.dto.ShowDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shows")
public class Show implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    private LocalDateTime showTime;

    @CreationTimestamp
    private LocalDateTime createdTimeStamp;

    @CreationTimestamp
    private LocalDateTime updatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    private Theater theater;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    public static ShowDto toDto(Show show){
        return ShowDto.builder()
                .showTime(show.getShowTime())
                .id(show.getShowId())
                .movieId(show.getMovie().getId())
                .theaterId(show.getTheater().getTheatreId())
                .build();
    }
}

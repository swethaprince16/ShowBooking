package com.showbooking.service;

import com.showbooking.dto.TheaterDto;
import com.showbooking.enums.SeatType;
import com.showbooking.exception.DataNotFoundException;
import com.showbooking.models.Theater;
import com.showbooking.models.TheaterSeat;
import com.showbooking.repository.TheaterRepository;
import com.showbooking.repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterSeatRepository theaterSeatRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public TheaterDto addTheater(TheaterDto theaterDto) {
        Theater theater = Theater.builder()
                .city(theaterDto.getCity())
                .theatreName(theaterDto.getTheaterName()).build();

        theater.setTheatreSeats(generateTheaterSeats());

        for (TheaterSeat seatsEntity : theater.getTheatreSeats()) {
            seatsEntity.setTheater(theater);
        }
        theater = theaterRepository.save(theater);

        return Theater.toDto(theater);



    }

    private List<TheaterSeat> generateTheaterSeats() {
        List<TheaterSeat> seats = new ArrayList<>();
        seats.add(createTheaterSeat("1A", SeatType.PREMIUM, 100));
        seats.add(createTheaterSeat("1B", SeatType.PREMIUM, 100));
        seats.add(createTheaterSeat("1C", SeatType.PREMIUM, 100));
        seats.add(createTheaterSeat("1D", SeatType.PREMIUM, 100));
        seats.add(createTheaterSeat("1E", SeatType.PREMIUM, 100));

        seats.add(createTheaterSeat("2A", SeatType.REGULAR, 80));
        seats.add(createTheaterSeat("2B", SeatType.REGULAR, 80));
        seats.add(createTheaterSeat("2C", SeatType.REGULAR, 80));
        seats.add(createTheaterSeat("2D", SeatType.REGULAR, 80));
        seats.add(createTheaterSeat("2E", SeatType.REGULAR, 80));

        return theaterSeatRepository.saveAll(seats);


    }

    private TheaterSeat createTheaterSeat(String s, SeatType seatType, Integer price) {
        return TheaterSeat.builder()
                .seatNumber(s)
                .type(seatType)
                .price(price)
                .build();

    }

    public TheaterDto getTheaterById(Integer id) {
        Theater theater = theaterRepository.findById(id).orElseThrow(
                ()-> new DataNotFoundException("no theater for id")
        );

        return Theater.toDto(theater);
    }
}

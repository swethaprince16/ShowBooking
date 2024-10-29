package com.showbooking.service;

import ch.qos.logback.core.util.StringUtil;
import com.showbooking.dto.ShowDto;
import com.showbooking.exception.DataNotFoundException;
import com.showbooking.models.*;
import com.showbooking.repository.MovieRepository;
import com.showbooking.repository.ShowRepository;
import com.showbooking.repository.ShowSeatRepository;
import com.showbooking.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private MovieRepository movieRepository;


    public ShowDto addShow(ShowDto showDto) {

        Movie movie = movieRepository.findById(showDto.getMovieId()).orElseThrow(
                ()-> new DataNotFoundException(String.format("no Movie found for movieId: %d", showDto.getMovieId()))
        );

        Theater theater = theaterRepository.findById(showDto.getTheaterId()).orElseThrow(
                ()-> new DataNotFoundException(String.format("no Theater found for theaterId: %d", showDto.getTheaterId()))
        );
        Show show =  Show.builder()
                .showTime(showDto.getShowTime())
                .theater(theater)
                .movie(movie)
                .build();
        show.setShowSeats(generateShowSeats(show.getTheater().getTheatreSeats()));

        for (ShowSeat seatsEntity : show.getShowSeats()) {
            seatsEntity.setShow(show);
        }
        show = showRepository.save(show);
        return Show.toDto(show);
    }

    private List<ShowSeat> generateShowSeats(List<TheaterSeat> theaterSeats) {
        List<ShowSeat> showSeats = new ArrayList<>();
        for(TheaterSeat seat: theaterSeats){
            ShowSeat showSeat = ShowSeat.builder()
                    .type(seat.getType())
                    .number(seat.getSeatNumber())
                    .rate(seat.getPrice())
                    .build();
            showSeats.add(showSeat);
        }

        return showSeatRepository.saveAll(showSeats);

    }

    @Cacheable(value = "shows", key = "#city + '-' + #theaterName + '-' + (#movie ?: '')")
    public List<ShowDto> searchShows(String city,String theaterName, String movie) {
        if(!StringUtils.hasText(city)){
            return new ArrayList<>();
        }
        List<Show> shows=new ArrayList<>();

        if(StringUtils.hasText(movie)){
            shows =  showRepository.findByCityAndMovie(city,movie);
        }else if(StringUtils.hasText(theaterName)){
            shows =  showRepository.findByCityAndTheater(city,theaterName);
        }
        else {
            shows = showRepository.findByCity(city);
        }

        return shows.stream().map(s->Show.toDto(s)).toList();

    }
}

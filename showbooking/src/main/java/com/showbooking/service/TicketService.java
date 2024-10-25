package com.showbooking.service;

import com.showbooking.dto.BookingDto;
import com.showbooking.dto.TicketDto;
import com.showbooking.enums.SeatType;
import com.showbooking.exception.DataNotFoundException;
import com.showbooking.models.Show;
import com.showbooking.models.ShowSeat;
import com.showbooking.models.Ticket;
import com.showbooking.models.Users;
import com.showbooking.repository.ShowRepository;
import com.showbooking.repository.TicketRepository;
import com.showbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;

    public TicketDto bookTicket(BookingDto bookingDto) {
        Optional<Users> user = userRepository.findById(bookingDto.getUserId());
        if(!user.isPresent()){
            throw new DataNotFoundException(String.format("no user found for id: %d", bookingDto.getUserId()));
        }
        Optional<Show> show = showRepository.findById(bookingDto.getShowId());
        if(!show.isPresent()){
            throw new DataNotFoundException(String.format("no show found for id: %d", bookingDto.getShowId()));
        }

        Set<String> requestedSeats = bookingDto.getSeatNumbers();
        List<ShowSeat> seatsForShow = show.get().getShowSeats();
        seatsForShow = seatsForShow
                .stream()
                .filter(seat ->
                        !seat.isBooked()
                        && seat.getType().equals(SeatType.valueOf(bookingDto.getSeatType()))
                        && requestedSeats.contains(seat.getNumber()))
                .toList();
        if(seatsForShow.size()!=requestedSeats.size()){
            throw new DataNotFoundException("Seats not available for booking");
        }

        Ticket ticket = Ticket.builder()
                .user(user.get())
                .show(show.get())
                .showSeats(seatsForShow)
                .build();

        Double amount = 0.0;
        String allocattedSeats  = "";

        for(ShowSeat showseat: seatsForShow){
            showseat.setBooked(true);
            showseat.setBookedAt(new Date());
            showseat.setTicket(ticket);

            amount+=showseat.getRate();
            allocattedSeats+=showseat.getNumber() + " ";
        }

        ticket.setAmount(amount);
        ticket.setAllocatedSeats(allocattedSeats);

        if(user.get().getTickets().isEmpty()){
            user.get().setTickets(new ArrayList<>());
        }

        user.get().getTickets().add(ticket);

        if(show.get().getTickets().isEmpty()){
            show.get().setTickets(new ArrayList<>());
        }

        show.get().getTickets().add(ticket);

        ticketRepository.save(ticket);

        return Ticket.toDto(ticket);
    }
}

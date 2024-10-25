package com.showbooking.controller;

import com.showbooking.dto.BookingDto;
import com.showbooking.dto.TicketDto;
import com.showbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDto> bookTicket(@RequestBody BookingDto bookingDto){
        return new ResponseEntity<>(ticketService.bookTicket(bookingDto), HttpStatus.OK);

    }
}

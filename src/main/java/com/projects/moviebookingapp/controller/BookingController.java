package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.exception.CustomBookingException;
import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomUserException;
import com.projects.moviebookingapp.exception.NotEnoughTicketsException;
import com.projects.moviebookingapp.model.dto.BookingDto;
import com.projects.moviebookingapp.model.entity.Booking;
import com.projects.moviebookingapp.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/details/{bookingId}")
    public ResponseEntity<Booking> getBookingDetails(@PathVariable long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingDetails(bookingId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Booking>> getAllBookings(@PathVariable long eventId) {
        return ResponseEntity.ok(bookingService.getAllBookings(eventId));
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable long bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @PutMapping("/update")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(booking));
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> bookTicket(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.bookTicket(bookingDto));

    }

    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable long customerId) {
        return ResponseEntity.ok(bookingService.getBookingHistory(customerId));
    }

}

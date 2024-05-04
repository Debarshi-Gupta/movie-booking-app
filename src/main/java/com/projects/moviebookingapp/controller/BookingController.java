package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.exception.CustomBookingException;
import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomUserException;
import com.projects.moviebookingapp.exception.NotEnoughTicketsException;
import com.projects.moviebookingapp.model.dto.BookingDto;
import com.projects.moviebookingapp.model.entity.Booking;
import com.projects.moviebookingapp.service.booking.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Operation(summary = "Get Booking Details REST API", description = "This REST API fetches a Booking by accepting bookingId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("/details/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> getBookingDetails(@PathVariable long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingDetails(bookingId));
    }

    @Operation(summary = "Get List of Booking Details REST API", description = "This REST API fetches a List of Booking by accepting eventId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Booking>> getAllBookings(@PathVariable long eventId) {
        return ResponseEntity.ok(bookingService.getAllBookings(eventId));
    }

    @Operation(summary = "Delete Booking Details REST API", description = "This REST API deletes a Booking by accepting bookingId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @DeleteMapping("/cancel/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> cancelBooking(@PathVariable long bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @Operation(summary = "Update Booking Details REST API", description = "This REST API updates a Booking by accepting Booking Object as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(booking));
    }

    @Operation(summary = "Book Ticket REST API", description = "This REST API allows users to book a ticket")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> bookTicket(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.bookTicket(bookingDto));

    }

    @Operation(summary = "Get Booking History REST API", description = "This REST API fetches a List of Booking by accepting userId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("/history/{customerId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable long userId) {
        return ResponseEntity.ok(bookingService.getBookingHistory(userId));
    }

}

package com.projects.moviebookingapp.service.booking;

import com.projects.moviebookingapp.exception.CustomBookingException;
import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomUserException;
import com.projects.moviebookingapp.exception.NotEnoughTicketsException;
import com.projects.moviebookingapp.model.dto.BookingDto;
import com.projects.moviebookingapp.model.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking getBookingDetails(long id) throws CustomBookingException;

    List<Booking> getBookingHistory(long userId) throws CustomBookingException, CustomUserException;

    Booking cancelBooking(long id) throws CustomBookingException, CustomEventException;

    Booking bookTicket(BookingDto bookingDto) throws NotEnoughTicketsException, CustomUserException, CustomEventException;

    Booking updateBooking(Booking booking);

    List<Booking> getAllBookings(long eventId) throws CustomEventException;
}

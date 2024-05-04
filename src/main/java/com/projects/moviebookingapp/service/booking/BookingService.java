package com.projects.moviebookingapp.service.booking;

import com.projects.moviebookingapp.exception.CustomBookingException;
import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomUserException;
import com.projects.moviebookingapp.exception.NotEnoughTicketsException;
import com.projects.moviebookingapp.model.dto.BookingDto;
import com.projects.moviebookingapp.model.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking getBookingDetails(long id);

    List<Booking> getBookingHistory(long userId);

    Booking cancelBooking(long id);

    Booking bookTicket(BookingDto bookingDto);

    Booking updateBooking(Booking booking);

    List<Booking> getAllBookings(long eventId);
}

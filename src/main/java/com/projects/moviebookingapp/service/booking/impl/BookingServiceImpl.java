package com.projects.moviebookingapp.service.booking.impl;

import com.projects.moviebookingapp.exception.CustomBookingException;
import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomUserException;
import com.projects.moviebookingapp.exception.NotEnoughTicketsException;
import com.projects.moviebookingapp.model.dto.BookingDto;
import com.projects.moviebookingapp.model.entity.Booking;
import com.projects.moviebookingapp.model.entity.Event;
import com.projects.moviebookingapp.model.entity.User;
import com.projects.moviebookingapp.repository.BookingRepository;
import com.projects.moviebookingapp.service.booking.BookingService;
import com.projects.moviebookingapp.service.booking.mapper.CustomBookingMapper;
import com.projects.moviebookingapp.service.event.EventService;
import com.projects.moviebookingapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CustomBookingMapper customBookingMapper;

    public Booking getBookingDetails(long id) throws CustomBookingException {
        return bookingRepository.findById(id).orElseThrow(() -> new CustomBookingException("Booking not found"));
    }

    @Override
    public List<Booking> getBookingHistory(long userId) throws CustomBookingException, CustomUserException {
        User user = null;

        user = userService.findById(userId);

        try {
            return bookingRepository.findAllByUser(user);
        } catch (Exception e) {
            throw new CustomBookingException("Booking not found");
        }
    }

    @Override
    public Booking cancelBooking(long id) throws CustomBookingException, CustomEventException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new CustomBookingException("Booking not found"));
        bookingRepository.delete(booking);
        Event event = eventService.getEventById(booking.getEvent().getId());
        event.setRemainingTickets(event.getRemainingTickets() + booking.getNumberOfTickets());
        eventService.updateEvent(event);
        return booking;
    }

    @Override
    public Booking bookTicket(BookingDto bookingDto) throws NotEnoughTicketsException, CustomUserException, CustomEventException {
        Event event = eventService.getEventById(bookingDto.getEventId());
        if (event.getRemainingTickets() < bookingDto.getNumberOfSeats()) {
            throw new NotEnoughTicketsException("Not enough tickets available");
        }
        Booking booking = customBookingMapper.mapToBooking(bookingDto);
        bookingRepository.save(booking);
        event.setRemainingTickets(event.getRemainingTickets() - bookingDto.getNumberOfSeats());
        eventService.updateEvent(event);
        return booking;
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings(long eventId) throws CustomEventException {
        Event event = eventService.getEventById(eventId);
        return bookingRepository.findAllByEvent(event);
    }

}

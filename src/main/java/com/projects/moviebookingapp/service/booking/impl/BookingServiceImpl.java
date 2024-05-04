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
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CustomBookingMapper customBookingMapper;

    public Booking getBookingDetails(long id) {

        Booking booking = null;

        try {
            log.info("Attempting to fetch Booking Object..");
            booking = bookingRepository.findById(id).orElse(null);
            log.info("Booking Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Booking");
            throw new CustomBookingException("Failed to fetch Booking");
        }

        if (Objects.isNull(booking)) {
            log.error("No Bookings found with id: {}", id);
            throw new CustomBookingException("No Bookings found with id: " + id);
        }

        return booking;
    }

    @Override
    public List<Booking> getBookingHistory(long userId) {

        User user = null;
        List<Booking> bookingList = null;

        try {
            log.info("Attempting to fetch User by id: {}", userId);
            user = userService.findById(userId);
            log.info("User Object fetched successfully!");
        } catch (Exception e) {
            log.info("Failed to fetch User Object");
            throw new CustomUserException("Failed to fetch User Object");
        }

        try {
            log.info("Attempting to fetch Bookings..");
            bookingList = bookingRepository.findAllByUser(user);
            log.info("Bookings fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Bookings");
            throw new CustomBookingException("Failed to fetch Bookings");
        }

        return bookingList;
    }

    @Override
    public Booking cancelBooking(long id) {

        Booking booking = null;
        Event event = null;

        try {
            log.info("Attempting to fetch Booking Object..");
            booking = bookingRepository.findById(id).orElse(null);
            log.info("Booking Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Booking Object");
            throw new CustomBookingException("Failed to fetch Booking Object");
        }

        if (Objects.isNull(booking)) {
            log.error("No Booking found with id: {}", id);
            throw new CustomBookingException("No Booking found with id: " + id);
        }

        try {
            log.info("Attempting to delete Booking Object..");
            bookingRepository.delete(booking);
            log.info("Booking Object deleted successfully!");
        } catch (Exception e) {
            log.error("Failed to delete Booking Object");
            throw new CustomBookingException("Failed to delete Booking Object");
        }

        try {
            log.info("Attempting to fetch Event Object..");
            event = eventService.getEventById(booking.getEvent().getId());
            log.info("Event Object fetched successfully!");
            event.setRemainingTickets(event.getRemainingTickets() + booking.getNumberOfTickets());
        } catch (Exception e) {
            log.error("Failed to fetch Event Object");
            throw new CustomEventException("Failed to fetch Event Object");
        }

        try {
            log.info("Attempting to update Event Object..");
            eventService.updateEvent(event);
            log.info("Event Object updated successfully!");
        } catch (Exception e) {
            log.error("Failed to update Event Object");
            throw new CustomEventException("Failed to update Event Object");
        }

        return booking;
    }

    @Override
    public Booking bookTicket(BookingDto bookingDto) {

        Event event = null;
        Booking savedBooking = null;

        try {
            log.info("Attempting to fetch Event Object..");
            event = eventService.getEventById(bookingDto.getEventId());
            log.info("Event Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Event Object");
            throw new CustomEventException("Failed to fetch Event Object");
        }

        if (event.getRemainingTickets() < bookingDto.getNumberOfSeats()) {
            log.error("Not enough tickets available");
            throw new NotEnoughTicketsException("Not enough tickets available");
        }

        Booking booking = customBookingMapper.mapToBooking(bookingDto);

        try {
            log.info("Attempting to save Booking Object..");
            savedBooking = bookingRepository.save(booking);
            log.info("Booking Object saved successfully!");
        } catch (Exception e) {
            log.error("Failed to save Booking Object");
            throw new CustomBookingException("Failed to save Booking Object");
        }

        event.setRemainingTickets(event.getRemainingTickets() - bookingDto.getNumberOfSeats());

        try {
            log.info("Attempting to update Event..");
            eventService.updateEvent(event);
            log.info("Event Object updated successfully");
        } catch (Exception e) {
            log.error("Failed to update Event Object");
            throw new CustomEventException("Failed to update Event Object");
        }

        return savedBooking;
    }

    @Override
    public Booking updateBooking(Booking booking) {

        Booking updatedBooking = null;

        try {
            log.info("Attempting to update Booking Object..");
            updatedBooking = bookingRepository.save(booking);
            log.info("Booking Object updated successfully!");
        } catch (Exception e) {
            log.error("Failed to update Booking Object");
            throw new CustomBookingException("Failed to update Booking Object");
        }

        return updatedBooking;
    }

    @Override
    public List<Booking> getAllBookings(long eventId) {

        Event event = null;
        List<Booking> bookingList = null;

        try {
            log.info("Attempting to fetch Event..");
            event = eventService.getEventById(eventId);
            log.info("Event Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Event with id: {}", eventId);
            throw new CustomEventException("Failed to fetch Event with id: " + eventId);
        }

        try {
            log.info("Attempting to fetch Bookings..");
            bookingList = bookingRepository.findAllByEvent(event);
            log.info("Bookings fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Bookings");
            throw new CustomBookingException("Failed to fetch Bookings");
        }

        return bookingList;
    }

}

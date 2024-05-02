package com.projects.moviebookingapp.service.booking.mapper;

import com.projects.moviebookingapp.model.dto.BookingDto;
import com.projects.moviebookingapp.model.entity.Booking;
import com.projects.moviebookingapp.model.entity.Event;
import com.projects.moviebookingapp.model.entity.User;
import com.projects.moviebookingapp.model.enums.BookingStatus;
import com.projects.moviebookingapp.repository.UserRepository;
import com.projects.moviebookingapp.service.event.EventService;
import com.projects.moviebookingapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomBookingMapper {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    public Booking mapToBooking(BookingDto bookingDto)
    {
        User user = userService.findById(bookingDto.getUserId());

        Event event = eventService.getEventById(bookingDto.getEventId());

        LocalDateTime bookingTime = LocalDateTime.now();

        return Booking.builder()
                .user(user)
                .event(event)
                .bookingTime(bookingTime)
                .bookingStatus(BookingStatus.PENDING)
                .numberOfTickets(bookingDto.getNumberOfSeats())
                .build();
    }
}

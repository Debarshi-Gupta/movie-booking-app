package com.projects.moviebookingapp.service.event.impl;

import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.exception.InvalidTimeSelectionException;
import com.projects.moviebookingapp.model.dto.EventDto;
import com.projects.moviebookingapp.model.entity.Event;
import com.projects.moviebookingapp.model.entity.Movie;
import com.projects.moviebookingapp.model.entity.Theatre;
import com.projects.moviebookingapp.repository.EventRepository;
import com.projects.moviebookingapp.service.event.EventService;
import com.projects.moviebookingapp.service.movie.MovieService;
import com.projects.moviebookingapp.service.theatre.TheatreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheatreService theatreService;

    @Override
    public List<Event> getAllEvents() {

        List<Event> eventList = null;

        try {
            log.info("Attempting to fetch Events..");
            eventList = eventRepository.findAll();
            log.info("Events fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Events");
            throw new CustomEventException("Failed to fetch Events");
        }

        if (Objects.isNull(eventList)) {
            log.error("No Events found");
            throw new CustomEventException("No Events found");
        }

        return eventList;
    }

    @Override
    public Event getEventById(long eventId) {

        Event event = null;

        try {
            log.info("Attempting to fetch Event Object..");
            event = eventRepository.findById(eventId).orElse(null);
            log.info("Event Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Event Object");
            throw new CustomEventException("Failed to fetch Event Object");
        }

        if (Objects.isNull(event)) {
            log.error("No Event found with id: {}", eventId);
            throw new CustomEventException("No Event found with id: " + eventId);
        }

        return event;
    }

    @Override
    public Event addEvent(EventDto eventDto) {

        Movie movie = null;
        Theatre theatre = null;
        List<Event> events = null;
        Event savedEvent = null;

        try {
            log.info("Attempting to fetch Movie by id: {}", eventDto.getMovieId());
            movie = movieService.getMovieById(eventDto.getMovieId());
            log.info("Movie Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Movie Object");
            throw new CustomMovieException("Failed to fetch Movie Object");
        }

        try {
            log.info("Attempting to fetch Theatre by id: {}", eventDto.getHallId());
            theatre = theatreService.getTheatreById(eventDto.getHallId());
            log.info("Theatre Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Theatre Object");
            throw new CustomTheatreException("Failed to fetch Theatre Object");
        }

        try {
            log.info("Attempting to fetch existing Events..");
            events = eventRepository.findByStartTimeBetweenAndTheatre(LocalDateTime.parse(eventDto.getStartTime()), LocalDateTime.parse(eventDto.getEndTime()), theatre);
            log.info("Existing Events fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Existing events");
            throw new CustomEventException("Failed to fetch Existing events");
        }

        if (!events.isEmpty()) {
            log.error("Event already exists at that time in the hall");
            throw new InvalidTimeSelectionException("Event already exists at that time in the hall");
        }

        Event newEvent = Event.builder()
                .movie(movie)
                .theatre(theatre)
                .remainingTickets(theatre.getCapacity())
                .price(eventDto.getPrice())
                .startTime(LocalDateTime.parse(eventDto.getStartTime()))
                .endTime(LocalDateTime.parse(eventDto.getEndTime()))
                .build();

        try {
            log.info("Attempting to save Event Object..");
            savedEvent = eventRepository.save(newEvent);
            log.info("Event Object saved successfully!");
        } catch (Exception e) {
            log.error("Failed to save Event Object");
            throw new CustomEventException("Failed to save Event Object");
        }

        return savedEvent;
    }

    @Override
    public Event updateEvent(Event event) {

        Event updatedEvent = null;

        try {
            log.info("Attempting to save Event Object..");
            updatedEvent = eventRepository.save(event);
            log.info("Event Object saved successfully!");
        } catch (Exception e) {
            log.error("Failed to save Event Object");
            throw new CustomEventException("Failed to save Event Object");
        }

        return updatedEvent;
    }

    @Override
    public String deleteEventById(long eventId) {

        try {
            log.info("Attempting to delete Event Object..");
            eventRepository.deleteById(eventId);
            log.info("Event Object deleted successfully!");
        } catch (Exception e) {
            log.info("Failed to delete Event Object");
            throw new CustomEventException("Failed to delete Event Object");
        }

        return "Event deleted successfully";
    }
}

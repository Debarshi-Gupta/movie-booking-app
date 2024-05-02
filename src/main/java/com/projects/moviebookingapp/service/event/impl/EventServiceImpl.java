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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheatreService theatreService;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(long eventId) throws CustomEventException {
        return eventRepository.findById(eventId).orElseThrow(() -> new CustomEventException("Event with id: " + eventId + " not found!"));
    }

    @Override
    public Event addEvent(EventDto eventDto) throws CustomMovieException, CustomTheatreException, InvalidTimeSelectionException {

        Movie movie = movieService.getMovieById(eventDto.getMovieId());
        Theatre theatre =  theatreService.getTheatreById(eventDto.getHallId());
        List<Event> events = eventRepository.findByStartTimeBetweenAndTheatre(LocalDateTime.parse(eventDto.getStartTime()), LocalDateTime.parse(eventDto.getEndTime()), theatre);
        if(!events.isEmpty()) {
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

        return eventRepository.save(newEvent);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEventById(long eventId) {
        eventRepository.deleteById(eventId);
    }
}

package com.projects.moviebookingapp.service.event;

import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.exception.InvalidTimeSelectionException;
import com.projects.moviebookingapp.model.dto.EventDto;
import com.projects.moviebookingapp.model.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventById(long eventId) throws CustomEventException;

    Event addEvent(EventDto eventDto) throws CustomMovieException, CustomTheatreException, InvalidTimeSelectionException;

    Event updateEvent(Event event);

    void deleteEventById(long eventId);
}

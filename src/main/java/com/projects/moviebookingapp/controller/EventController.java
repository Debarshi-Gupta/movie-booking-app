package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.exception.CustomEventException;
import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.exception.InvalidTimeSelectionException;
import com.projects.moviebookingapp.model.dto.EventDto;
import com.projects.moviebookingapp.model.entity.Event;
import com.projects.moviebookingapp.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> addEvent(@RequestBody EventDto eventDto) {
        Event newEvent = eventService.addEvent(eventDto);
        return ResponseEntity.ok(newEvent);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event Updatedevent) {
        Event event = eventService.updateEvent(Updatedevent);
        return ResponseEntity.ok(event);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> event = eventService.getAllEvents();
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Event> getEventById(@PathVariable long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        String response = eventService.deleteEventById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

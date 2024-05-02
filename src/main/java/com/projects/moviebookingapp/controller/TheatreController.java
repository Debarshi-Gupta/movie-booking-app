package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.model.entity.Theatre;
import com.projects.moviebookingapp.service.theatre.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/theatres")
@CrossOrigin
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addCinemaHall(@RequestBody Theatre theatre) {
        Theatre newTheatre = theatreService.addTheatre(theatre);
        return ResponseEntity.ok(newTheatre);
    }

    @PutMapping("/{theatreId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> updateCinemaHall(@PathVariable Long theatreId, @RequestBody Theatre updatedTheatre) {

        Theatre theatre = theatreService.updateTheatre(updatedTheatre);
        return ResponseEntity.ok(updatedTheatre);
    }

    @DeleteMapping("/{theatreId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCinemaHall(@PathVariable Long theatreId) {
        theatreService.deleteTheatre(theatreId);
        return new ResponseEntity<>("Cinema Hall deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Theatre>> getAllCinemaHalls() {
        List<Theatre> theatreList = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatreList);
    }

    @GetMapping("/{theatreId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Theatre> getCinemaHallById(@PathVariable Long theatreId) throws CustomTheatreException {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theatre);
    }
}

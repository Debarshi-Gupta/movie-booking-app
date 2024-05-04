package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.model.entity.Theatre;
import com.projects.moviebookingapp.service.theatre.TheatreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create Theatre Details REST API", description = "This REST API creates a Theatre by accepting Theatre Object as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
        Theatre newTheatre = theatreService.addTheatre(theatre);
        return ResponseEntity.ok(newTheatre);
    }

    @Operation(summary = "Update Movie Details REST API", description = "This REST API updates a Theatre by accepting theatreId and Theatre Object as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PutMapping("/{theatreId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long theatreId, @RequestBody Theatre updatedTheatre) {

        Theatre theatre = theatreService.updateTheatre(updatedTheatre);
        return ResponseEntity.ok(updatedTheatre);
    }

    @Operation(summary = "Delete Theatre Details REST API", description = "This REST API deletes a Theatre by accepting theatreId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @DeleteMapping("/{theatreId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long theatreId) {
        String response = theatreService.deleteTheatre(theatreId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get List of Theatre Details REST API", description = "This REST API fetches all theatres")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatreList = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatreList);
    }

    @Operation(summary = "Get Theatre Details REST API", description = "This REST API fetches a theatre by accepting theatreId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("/{theatreId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long theatreId) {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theatre);
    }
}

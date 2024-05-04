package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.model.dto.MovieDto;
import com.projects.moviebookingapp.model.entity.Movie;
import com.projects.moviebookingapp.service.movie.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(summary = "Get List of Movie Details REST API", description = "This REST API fetches all movies")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @Operation(summary = "Get Movie Details REST API", description = "This REST API fetches a Movie by accepting movieId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @Operation(summary = "Create Movie Details REST API", description = "This REST API creates a Movie by accepting Movie Object as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.createMovie(movieDto));
    }

    @Operation(summary = "Update Movie Details REST API", description = "This REST API updates a Movie by accepting Movie Object as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movieDetails) {
        return ResponseEntity.ok(movieService.updateMovie(movieDetails));
    }

    @Operation(summary = "Delete Movie Details REST API", description = "This REST API deletes a Movie by accepting movieId as parameter")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        String response = movieService.deleteMovie(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.projects.moviebookingapp.service.movie;

import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.model.dto.MovieDto;
import com.projects.moviebookingapp.model.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie createMovie(MovieDto movieDto);

    Movie updateMovie(Movie movie);

    String deleteMovie(Long movieId);
}

package com.projects.moviebookingapp.service.movie.impl;

import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.model.dto.MovieDto;
import com.projects.moviebookingapp.model.entity.Movie;
import com.projects.moviebookingapp.repository.MovieRepository;
import com.projects.moviebookingapp.service.movie.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {

        Movie movie = null;

        try {
            log.info("Attempting to fetch Movie Object..");
            movie = movieRepository.findById(id).orElse(null);
            log.info("Movie Object fetched successfully!");
        } catch (Exception e) {
            log.error("Failed to fetch Movie Object");
            throw new CustomMovieException("Failed to fetch Movie Object");
        }

        if (Objects.isNull(movie)) {
            log.error("No Movie found with id: {}", id);
            throw new CustomMovieException("No Movie found with id: " + id);
        }

        return movie;
    }

    @Override
    public Movie createMovie(MovieDto movieDto) {

        Movie savedMovie = null;

        Movie movie = Movie.builder()
                .name(movieDto.getName())
                .description(movieDto.getDescription())
                .durationInMinutes(movieDto.getDurationInMinutes())
                .director(movieDto.getDirector())
                .producer(movieDto.getProducer())
                .genres(movieDto.getGenre())
                .releaseDate(LocalDateTime.parse(movieDto.getReleaseDate()))
                .build();

        try {
            log.info("Attempting to save Movie Object..");
            savedMovie = movieRepository.save(movie);
            log.info("Movie Object saved successfully");
        } catch (Exception e) {
            log.error("Failed to save Movie Object");
            throw new CustomMovieException("Failed to save Movie Object");
        }

        return savedMovie;
    }

    @Override
    public Movie updateMovie(Movie movie) {

        Movie updatedMovie = null;

        try {
            log.info("Attempting to update Movie Object..");
            updatedMovie = movieRepository.save(movie);
            log.info("Movie Object updated successfully");
        } catch (Exception e) {
            log.error("Failed to update Movie Object");
            throw new CustomMovieException("Failed to update Movie Object");
        }

        return updatedMovie;
    }

    @Override
    public String deleteMovie(Long movieId) {

        try {
            log.info("Attempting to delete Movie Object..");
            movieRepository.deleteById(movieId);
            log.info("Movie Object deleted successfully");
        } catch (Exception e) {
            log.error("Failed to delete Movie Object");
            throw new CustomMovieException("Failed to delete Movie Object");
        }

        return "Movie deleted successfully!";
    }

}

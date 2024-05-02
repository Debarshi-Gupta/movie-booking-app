package com.projects.moviebookingapp.service.movie.impl;

import com.projects.moviebookingapp.exception.CustomMovieException;
import com.projects.moviebookingapp.model.dto.MovieDto;
import com.projects.moviebookingapp.model.entity.Movie;
import com.projects.moviebookingapp.repository.MovieRepository;
import com.projects.moviebookingapp.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) throws CustomMovieException {
        return movieRepository.findById(id).orElseThrow(() -> new CustomMovieException("Movie with id: " + id + " not found!"));
    }

    @Override
    public Movie createMovie(MovieDto movieDto) {

        Movie movie = Movie.builder()
                .name(movieDto.getName())
                .description(movieDto.getDescription())
                .durationInMinutes(movieDto.getDurationInMinutes())
                .director(movieDto.getDirector())
                .producer(movieDto.getProducer())
                .genres(movieDto.getGenre())
                .releaseDate(LocalDateTime.parse(movieDto.getReleaseDate()))
                .build();

        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}

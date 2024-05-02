package com.projects.moviebookingapp.service.theatre;

import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.model.entity.Theatre;

import java.util.List;

public interface TheatreService {

    Theatre addTheatre(Theatre Theatre);

    Theatre updateTheatre(Theatre updatedTheatre);

    void deleteTheatre(Long hallId);

    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long hallId) throws CustomTheatreException;
}

package com.projects.moviebookingapp.service.theatre;

import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.model.entity.Theatre;

import java.util.List;

public interface TheatreService {

    Theatre addTheatre(Theatre Theatre);

    Theatre updateTheatre(Theatre updatedTheatre);

    String deleteTheatre(Long theatreId);

    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long theatreId);
}

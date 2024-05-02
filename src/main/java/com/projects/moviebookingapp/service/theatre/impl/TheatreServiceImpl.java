package com.projects.moviebookingapp.service.theatre.impl;

import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.model.entity.Theatre;
import com.projects.moviebookingapp.repository.TheatreRepository;
import com.projects.moviebookingapp.service.theatre.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public Theatre addTheatre(Theatre Theatre) {
        return theatreRepository.save(Theatre);
    }

    @Override
    public Theatre updateTheatre(Theatre updatedTheatre) {
        return theatreRepository.save(updatedTheatre);
    }

    @Override
    public void deleteTheatre(Long hallId) {
        theatreRepository.deleteById(hallId);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long hallId) throws CustomTheatreException {
        return theatreRepository.findById(hallId).orElseThrow(() -> new CustomTheatreException("Cinema Hall not found."));
    }
}

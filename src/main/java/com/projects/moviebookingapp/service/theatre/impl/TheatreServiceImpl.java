package com.projects.moviebookingapp.service.theatre.impl;

import com.projects.moviebookingapp.exception.CustomTheatreException;
import com.projects.moviebookingapp.model.entity.Theatre;
import com.projects.moviebookingapp.repository.TheatreRepository;
import com.projects.moviebookingapp.service.theatre.TheatreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public Theatre addTheatre(Theatre theatre) {

        Theatre savedTheatre = null;

        try {
            log.info("Attempting to save Theatre Object..");
            savedTheatre = theatreRepository.save(theatre);
            log.info("Theatre Object saved successfully!");
        } catch (Exception e) {
            log.error("Failed to save Theatre Object");
            throw new CustomTheatreException("Failed to save Theatre Object");
        }

        return savedTheatre;
    }

    @Override
    public Theatre updateTheatre(Theatre updatedTheatre) {

        Theatre savedTheatre = null;

        try {
            log.info("Attempting to update Theatre Object..");
            savedTheatre = theatreRepository.save(updatedTheatre);
            log.info("Theatre Object updated successfully!");
        } catch (Exception e) {
            log.error("Failed to update Theatre Object");
            throw new CustomTheatreException("Failed to update Theatre Object");
        }

        return savedTheatre;
    }

    @Override
    public String deleteTheatre(Long theatreId) {

        try {
            log.info("Attempting to delete Theatre Object..");
            theatreRepository.deleteById(theatreId);
            log.info("Theatre Object saved successfully!");
        } catch (Exception e) {
            log.error("Failed to save Theatre Object");
            throw new CustomTheatreException("Failed to save Theatre Object");
        }

        return "Theatre deleted successfully";
    }

    @Override
    public List<Theatre> getAllTheatres() {

        List<Theatre> theatreList = null;

        try {
            log.info("Attempting to fetch Theatres..");
            theatreList = theatreRepository.findAll();
            log.info("Theatres fetched successfully");
        } catch (Exception e) {
            log.error("Failed to fetch Theatres");
            throw new CustomTheatreException("Failed to fetch Theatres");
        }

        if (Objects.isNull(theatreList)) {
            log.error("No Theatres found");
            throw new CustomTheatreException("No Theatres found");
        }

        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long theatreId) {

        Theatre theatre = null;

        try {
            log.info("Attempting to fetch Theatre Object");
            theatre = theatreRepository.findById(theatreId).orElse(null);
            log.info("Theatre Object fetched successfully");
        } catch (Exception e) {
            log.error("Failed to fetch Theatre Object");
            throw new CustomTheatreException("Failed to fetch Theatre Object");
        }

        if (Objects.isNull(theatre)) {
            log.error("No Theatre found with id: {}", theatreId);
            throw new CustomTheatreException("No Theatre found with id: " + theatreId);
        }

        return theatre;
    }
}

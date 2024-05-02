package com.projects.moviebookingapp.repository;

import com.projects.moviebookingapp.model.entity.Event;
import com.projects.moviebookingapp.model.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStartTimeBetweenAndTheatre(LocalDateTime startTime, LocalDateTime endTime, Theatre theatre);
}

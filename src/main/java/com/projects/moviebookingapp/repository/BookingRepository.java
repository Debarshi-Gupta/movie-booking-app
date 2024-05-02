package com.projects.moviebookingapp.repository;

import com.projects.moviebookingapp.model.entity.Booking;
import com.projects.moviebookingapp.model.entity.Event;
import com.projects.moviebookingapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUser(User user);
    List<Booking> findAllByEvent(Event event);
}

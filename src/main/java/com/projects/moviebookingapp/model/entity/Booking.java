package com.projects.moviebookingapp.model.entity;

import com.projects.moviebookingapp.model.enums.BookingStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "number_of_tickets")
    private int numberOfTickets;

    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;
}

package com.projects.moviebookingapp.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookingDto {

    private long userId;

    private long eventId;

    private int numberOfSeats;
}

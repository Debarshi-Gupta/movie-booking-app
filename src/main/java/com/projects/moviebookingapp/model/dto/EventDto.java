package com.projects.moviebookingapp.model.dto;

import com.projects.moviebookingapp.model.enums.Genre;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventDto {

    private String startTime;

    private String endTime;

    private long movieId;

    private long hallId;

    private double price;
}

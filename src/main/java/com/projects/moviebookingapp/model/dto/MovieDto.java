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
public class MovieDto {

    private String name;

    private String description;

    private List<Genre> genre;

    private int durationInMinutes;

    private String director;

    private String producer;

    private String releaseDate;
}
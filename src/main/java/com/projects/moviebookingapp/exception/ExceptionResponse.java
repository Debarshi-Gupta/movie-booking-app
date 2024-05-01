package com.projects.moviebookingapp.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExceptionResponse {

    private String message;

    private LocalDateTime timestamp;

    private String path;
}

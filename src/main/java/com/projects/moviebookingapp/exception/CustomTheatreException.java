package com.projects.moviebookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomTheatreException extends RuntimeException{

    public CustomTheatreException(String message)
    {
        super(message);
    }
}

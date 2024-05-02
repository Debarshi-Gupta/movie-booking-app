package com.projects.moviebookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughTicketsException extends RuntimeException{

    public NotEnoughTicketsException(String message)
    {
        super(message);
    }
}

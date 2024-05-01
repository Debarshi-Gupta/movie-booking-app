package com.projects.moviebookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomUserException extends RuntimeException{

    public CustomUserException(String message)
    {
        super(message);
    }
}

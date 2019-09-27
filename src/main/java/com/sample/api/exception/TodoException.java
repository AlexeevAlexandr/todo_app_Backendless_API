package com.sample.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoException extends RuntimeException{

    public TodoException(String message){
        super(message);
    }
}

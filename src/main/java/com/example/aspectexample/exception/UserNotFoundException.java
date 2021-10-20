package com.example.aspectexample.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Error: User Not Found!")
public class UserNotFoundException extends RuntimeException{
}

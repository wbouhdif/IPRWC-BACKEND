package com.example.IPRWCbackend.exceptions;

public class EmailTakenException extends Exception {


    private static final String message = "Email address is already in use.";

    public EmailTakenException() {
        super(message);
    }

}

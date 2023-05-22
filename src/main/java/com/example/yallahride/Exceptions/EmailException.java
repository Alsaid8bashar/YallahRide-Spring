package com.example.yallahride.Exceptions;

public class EmailException extends RuntimeException{
    public EmailException(){
        super("Email could not be sent to end point user");
    }
}

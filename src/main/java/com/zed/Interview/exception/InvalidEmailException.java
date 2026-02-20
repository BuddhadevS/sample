package com.zed.Interview.exception;

public class InvalidEmailException extends  RuntimeException{
    public InvalidEmailException(String message){
        super(message);
    }
}

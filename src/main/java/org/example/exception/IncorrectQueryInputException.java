package org.example.exception;

public class IncorrectQueryInputException extends Exception{
    public IncorrectQueryInputException(String message) {
        super(message);
    }
}

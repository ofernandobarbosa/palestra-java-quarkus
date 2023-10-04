package br.edu.ifrs.palestra.service.exceptions;

public class DatabaseException extends RuntimeException{
    
    public DatabaseException(String message){
        super(message);
    }
}

package br.edu.ifrs.palestra.service;

import jakarta.ejb.ApplicationException;

@ApplicationException
public class ServiceException extends RuntimeException  {
    
    public ServiceException(String message) {
        super(message);
    }
    
}

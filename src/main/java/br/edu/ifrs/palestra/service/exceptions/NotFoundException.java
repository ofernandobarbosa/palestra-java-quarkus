package br.edu.ifrs.palestra.service.exceptions;

// import jakarta.ejb.ApplicationException;

// @ApplicationException
public class NotFoundException extends RuntimeException  {

    public NotFoundException(String message, Long id) {
        super(message + " Id -> " + id);
    }

    public NotFoundException(Long id) {
        super("Recurso não encontrado. Id -> " + id);
    }

}

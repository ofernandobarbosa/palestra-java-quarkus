package br.edu.ifrs.palestra.infrastructure;

import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import br.edu.ifrs.palestra.dto.ExceptionDTO;
import br.edu.ifrs.palestra.service.exceptions.DatabaseException;
import br.edu.ifrs.palestra.service.exceptions.NotFoundException;

public class ExceptionHandlerController {

    @ServerExceptionMapper(NotFoundException.class)
    public RestResponse<Object> threatNotFound404(NotFoundException exception) {
        Response.Status respStatus = Response.Status.NOT_FOUND;
        ExceptionDTO e = new ExceptionDTO(exception.getMessage(), respStatus.getStatusCode());
        return RestResponse.status(respStatus, e);
    }

    @ServerExceptionMapper(DatabaseException.class)
    public RestResponse<Object> threatDataBaseError(DatabaseException exception) {
        Response.Status respStatus = Response.Status.BAD_REQUEST;
        ExceptionDTO e = new ExceptionDTO(exception.getMessage(), respStatus.getStatusCode());
        return RestResponse.status(respStatus, e);
    }

    // @ServerExceptionMapper(Exception.class)
    // public RestResponse<Object> threatStandardError(Exception exception) {
    //     Response.Status respStatus = Response.Status.INTERNAL_SERVER_ERROR;
    //     ExceptionDTO e = new ExceptionDTO(exception.getMessage(), respStatus.getStatusCode());
    //     return RestResponse.status(respStatus, e);
    // }
}
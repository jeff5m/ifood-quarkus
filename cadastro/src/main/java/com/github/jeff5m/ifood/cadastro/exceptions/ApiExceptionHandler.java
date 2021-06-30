package com.github.jeff5m.ifood.cadastro.exceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ConstraintViolationResponse.of(ex)).build();
    }
}

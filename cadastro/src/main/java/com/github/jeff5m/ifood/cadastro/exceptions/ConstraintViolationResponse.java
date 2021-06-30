package com.github.jeff5m.ifood.cadastro.exceptions;


import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ConstraintViolationResponse {

    private final List<ConstraintViolationImpl> violations = new ArrayList<>();

    ConstraintViolationResponse(ConstraintViolationException ex) {
        ex.getConstraintViolations().forEach(violation -> violations.add(ConstraintViolationImpl.of(violation)));
    }

    public static ConstraintViolationResponse of(ConstraintViolationException ex) {
        return new ConstraintViolationResponse(ex);
    }

    public List<ConstraintViolationImpl> getViolations() {
        return violations;
    }

}

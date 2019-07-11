package com.eolivenza.modules.baseProject.domain.model;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author David Gracia Celemendi
 */
public class ModelValidationException extends RuntimeException {

    private final transient Set<ConstraintViolation<Object>> constraintViolationSet;

    public ModelValidationException(Set<ConstraintViolation<Object>> constraintViolationSet) {
        super(ModelExceptionCodes.VALIDATION_EXCEPTION);
        this.constraintViolationSet = constraintViolationSet;
    }

    public Set<ConstraintViolation<Object>> getConstraintViolationSet() {
        return constraintViolationSet;
    }

    @Override
    public String toString() {
        return "Constraint violation in domain: " +
                constraintViolationSet
                .stream()
                .map(ConstraintViolation::getMessage)
                .reduce((x,y) -> String.format(", %s, %s", x,y))
                .orElse("(empty constraint set)");
    }
}

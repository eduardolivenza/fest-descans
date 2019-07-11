package com.eolivenza.modules.baseProject.domain;

import com.eolivenza.modules.baseProject.domain.model.ModelValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author David Gracia Celemendi
 */
public class ValidatorWrapper {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidatorWrapper() {
    }

    /**
     * Same behaviour that {@link Validator#validate(Object, Class[])}, except that it throws a
     * {@link ModelValidationException} containing all {@link ConstraintViolation}s (accessible via {@link ModelValidationException#getConstraintViolationSet()}) found by the wrapped
     * {@link Validator} instead of returning a set of {@link ConstraintViolation}s
     *
     * @param object See {@link Validator#validate(Object, Class[])}
     * @param groups See {@link Validator#validate(Object, Class[])}
     * @param <T>    See {@link Validator#validate(Object, Class[])}
     * @throws ModelValidationException containing all {@link ConstraintViolation}s found by the wrapped {@link Validator}
     */
    public static <T> void validate(T object, Class<?>... groups) {
        processConstraintViolationSet(validator.validate(object, groups));
    }

    /**
     * Same behaviour that {@link Validator#validateProperty(Object, String, Class[])}, except that it throws a
     * {@link ModelValidationException} containing all {@link ConstraintViolation}s (accessible via {@link ModelValidationException#getConstraintViolationSet()}) found by the wrapped
     * {@link Validator} instead of returning a set of {@link ConstraintViolation}s
     *
     * @param object       See {@link Validator#validateProperty(Object, String, Class[])}
     * @param propertyName See {@link Validator#validateProperty(Object, String, Class[])}
     * @param groups       See {@link Validator#validateProperty(Object, String, Class[])}
     * @param <T>          See {@link Validator#validateProperty(Object, String, Class[])}
     * @throws ModelValidationException containing all {@link ConstraintViolation}s found by the wrapped {@link Validator}
     */
    public static <T> void validateProperty(T object, String propertyName, Class<?>... groups) {
        processConstraintViolationSet(validator.validateProperty(object, propertyName, groups));
    }

    private static void processConstraintViolationSet(Set<ConstraintViolation<Object>> constraintViolationSet) {
        if (!constraintViolationSet.isEmpty()) {
            throw new ModelValidationException(constraintViolationSet);
        }
    }
}

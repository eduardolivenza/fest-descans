package com.eolivenza.modules.baseProject.domain.model;

import org.junit.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;


public class ModelValidationExceptionTest {

    @Test
    public void call_getConstraintViolationSet() {
        ModelValidationException modelValidationException = new ModelValidationException(new HashSet<>());

        assertThat(modelValidationException.getConstraintViolationSet()).isNotNull();
    }
}
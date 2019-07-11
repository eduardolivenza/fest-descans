package com.eolivenza.modules.baseProject.domain;

import com.eolivenza.modules.baseProject.domain.model.ModelValidationException;
import org.junit.Test;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ValidateableTest {

    @Test
    public void assert_validate_throw_exception_when_constraint_violation() {
        assertThatExceptionOfType(ModelValidationException.class)
                .isThrownBy(() -> new ImplementorB(-1))
                .satisfies(e -> assertThat(e).isNotNull());
    }

    @Test
    public void assert_validateProperty_throw_exception_when_constraint_violation() {
        assertThatExceptionOfType(ModelValidationException.class)
                .isThrownBy(() -> new ImplementorA(-1))
                .satisfies(e -> assertThat(e).isNotNull());
    }

    static class ImplementorA implements Validateable {

        @NotNull
        @Min(1)
        @Positive
        private int propertyA;

        public ImplementorA(int propertyA) {
            setPropertyA(propertyA);
        }

        private void setPropertyA(int propertyA) {
            this.propertyA = propertyA;
            validateProperty(this, "propertyA");
        }
    }

    static class ImplementorB implements Validateable {

        @NotNull
        @Min(1)
        @Positive
        private final int propertyA;

        ImplementorB(int propertyA) {
            this.propertyA = propertyA;
            validate(this);
        }
    }

}
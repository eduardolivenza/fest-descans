package com.eolivenza.modules.baseProject.domain.model;

import org.junit.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


public class ValueObjectTest {

    @Test
    public void equals_checks_property_values() {
        StubValue instance1 = new StubValue(true);
        StubValue instance2 = new StubValue(false);
        StubValue instance3 = new StubValue(false);

        assertThat(instance1.equals(instance2)).isFalse();
        assertThat(instance2.equals(instance3)).isTrue();
    }

    @Test
    public void hashCode_is_called() {
        StubValue instance1 = new StubValue(true);
        instance1.hashCode();
    }

    static class StubValue extends ValueObject<StubValue> {

        private Boolean myProperty;

        public StubValue(Boolean myProperty) {
            this.myProperty = myProperty;
        }

        @Override
        public int hashCodeCalculation() {
            return 0;
        }

        @Override
        public boolean hasSamePropertyValues(StubValue other) {
            return Objects.equals(myProperty, other.myProperty);
        }

    }
}
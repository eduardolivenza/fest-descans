package com.eolivenza.modules.baseProject.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelObjectTest {

    @Test
    public void assert_same_references_are_equal() {
        TypeA a = new TypeA();
        assertThat(a.equals(a)).isTrue();
    }

    @Test
    public void assert_is_not_equal_to_null() {
        TypeA a = new TypeA();
        assertThat(a.equals(null)).isFalse();
    }

    @Test
    public void assert_different_types_are_not_equal() {
        TypeA a = new TypeA();
        assertThat(a.equals(new TypeB())).isFalse();
    }

    static class TypeA extends ModelObject {

        @Override
        public int hashCodeCalculation() {
            return 0;
        }
    }

    static class TypeB extends ModelObject {

        @Override
        public int hashCodeCalculation() {
            return 0;
        }
    }

}
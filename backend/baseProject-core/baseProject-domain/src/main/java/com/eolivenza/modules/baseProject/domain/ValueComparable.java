package com.eolivenza.modules.baseProject.domain;

/**
 * @param <T> the type whose property values to compare
 */
@FunctionalInterface
public interface ValueComparable<T> {

    /**
     * This method must return {@code true} or {@code false} depending on whether the implementing instance has
     * the same property values that {@code other}.
     *
     * @param other an object to compare property values against
     * @return true or false whether the implementing instance has the same property values that {@code other}
     */
    boolean hasSamePropertyValues(T other);

}

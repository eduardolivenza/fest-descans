package com.eolivenza.modules.baseProject.domain;

/**
 * @param <T> the type whose identity to compare
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface IdentityComparable<T> {

    /**
     * This method must return {@code true} or {@code false} depending on whether the implementing instance has
     * the same identity that {@code other}.
     *
     * @param other an object to compare identity against
     * @return true or false whether the implementing instance has the same identity that {@code other}
     */
    boolean hasSameIdentity(T other);

}

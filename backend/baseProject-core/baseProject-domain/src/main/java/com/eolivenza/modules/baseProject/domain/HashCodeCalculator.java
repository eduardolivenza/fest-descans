package com.eolivenza.modules.baseProject.domain;

/**
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface HashCodeCalculator {

    /**
     * This method must return the hash code of the implementing instance.
     *
     * @return the hash code of the implementing instance
     */
    int hashCodeCalculation();

}

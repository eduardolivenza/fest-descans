package com.eolivenza.modules.baseProject.domain.model;

import com.eolivenza.modules.baseProject.domain.HashCodeCalculator;

/**
 * Base class for Entities and Value Objects (an other kind of model objects).
 *
 * @author David Gracia Celemendi
 */
public abstract class ModelObject implements HashCodeCalculator {

    @Override
    public boolean equals(Object o) {
        return super.equals(o) || (o != null && getClass() == o.getClass());
    }

    @Override
    public int hashCode() {
        return hashCodeCalculation();
    }
}
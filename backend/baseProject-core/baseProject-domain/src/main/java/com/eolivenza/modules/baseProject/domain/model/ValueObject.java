package com.eolivenza.modules.baseProject.domain.model;

import com.eolivenza.modules.baseProject.domain.Validateable;
import com.eolivenza.modules.baseProject.domain.ValueComparable;

/**
 * @param <T> the value object type
 */
public abstract class ValueObject<T> extends ModelObject implements ValueComparable<T>, Validateable {

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && hasSamePropertyValues((T) o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

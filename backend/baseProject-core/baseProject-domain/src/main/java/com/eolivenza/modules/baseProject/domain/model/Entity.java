package com.eolivenza.modules.baseProject.domain.model;

import com.eolivenza.modules.baseProject.domain.IdentityComparable;
import com.eolivenza.modules.baseProject.domain.Validateable;

/**
 * @param <T> the entity type
 */
public abstract class Entity<T> extends ModelObject implements IdentityComparable<T>, Validateable {

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && hasSameIdentity((T) o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

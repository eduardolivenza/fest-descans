package com.eolivenza.modules.baseProject.application.repositories.generics;

/**
 * This interface is a repository that can only update one instance per invocation
 *
 * @param <EntityType> the type of the entity
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface UpdateRepository<EntityType> {

    /**
     * This method must update the domain {@link EntityType} in the repository
     *
     * @param entity the domain entity to update
     * @return the updated domain entity
     */
    EntityType update(EntityType entity);
}

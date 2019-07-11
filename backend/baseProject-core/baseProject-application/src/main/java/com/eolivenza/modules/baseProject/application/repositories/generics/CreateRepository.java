package com.eolivenza.modules.baseProject.application.repositories.generics;

/**
 * This interface is a repository that can only create one instance per invocation
 *
 * @param <EntityType> the type of the entity
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface CreateRepository<EntityType> {

    /**
     * This method must create a new instance of domain {@link EntityType} into the repository.
     *
     * @param entity the domain entity to create
     * @return the created domain entity
     */
    EntityType create(EntityType entity);

}

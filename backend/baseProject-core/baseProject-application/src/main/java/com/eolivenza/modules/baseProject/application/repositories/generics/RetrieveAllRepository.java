package com.eolivenza.modules.baseProject.application.repositories.generics;

import java.util.List;

/**
 * This interface is a repository that can only retrieve all instances
 *
 * @param <EntityType> the type of the entity
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface RetrieveAllRepository<EntityType> {

    /**
     * This method must return a list of domain {@link EntityType} instances from a repository.
     *
     * @return a list of domain {@link EntityType} instances
     */
    List<EntityType> retrieveAll();
}

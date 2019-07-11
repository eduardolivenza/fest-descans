package com.eolivenza.modules.baseProject.application.repositories.generics;

/**
 * This interface is a CRUD repository.
 *
 * @param <EntityType>   the type of the entity
 * @param <IdentityType> the type of the identity of the entity
 * @author David Gracia Celemendi
 */
public interface Repository<EntityType, IdentityType> extends
        CreateRepository<EntityType>,
        RetrieveRepository<EntityType, IdentityType>,
        UpdateRepository<EntityType>,
        DeleteRepository<IdentityType> {
}

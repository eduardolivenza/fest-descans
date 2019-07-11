package com.eolivenza.modules.baseProject.application.repositories.generics;

/**
 * This interface is a repository that can only retrieve one instance per invocation
 *
 * @param <EntityType>   the type of the entity
 * @param <IdentityType> the type of the identity of the entity
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface RetrieveRepository<EntityType, IdentityType> {

    /**
     * This method must return the domain {@link EntityType} instance (identified with {@code identity}) from a repository.
     *
     * @param identity the identity of the domain entity instance
     * @return the domain {@link EntityType} instance identified with {@code identity}
     */
    EntityType retrieve(IdentityType identity);

}

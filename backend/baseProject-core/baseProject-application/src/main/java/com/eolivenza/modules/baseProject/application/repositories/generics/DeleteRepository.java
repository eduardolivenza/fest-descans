package com.eolivenza.modules.baseProject.application.repositories.generics;

/**
 * This interface is a repository that can only delete one instance per invocation
 *
 * @param <IdentityType> the type of the identity of the entity
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface DeleteRepository<IdentityType> {

    /**
     * This method must delete the domain entity (identified with {@code identity}) from the repository.
     *
     * @param identity the identity of the domain entity
     */
    void delete(IdentityType identity);

}

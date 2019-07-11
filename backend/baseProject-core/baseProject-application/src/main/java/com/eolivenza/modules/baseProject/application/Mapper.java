package com.eolivenza.modules.baseProject.application;

/**
 * This interface is a mapper between domain objects and adapter objects
 *
 * @param <DomainType> the domain type
 * @param <AdapterType> the adapter type
 * @author David Gracia Celemendi
 */
public interface Mapper<DomainType, AdapterType> {

    /**
     * This method must map an adapter object to a domain object
     *
     * @param object the adapter object
     * @return a domain object
     */
    DomainType toDomain(AdapterType object);

    /**
     * This method must map a domain object to an adapter object
     *
     * @param object the domain object
     * @return an adapter object
     */
    AdapterType fromDomain(DomainType object);

}

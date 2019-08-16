package com.eolivenza.modules.baseProject.repositories.domain.suppliers;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SupplierMapper implements Mapper<Supplier, SupplierJpa> {


    public SupplierMapper()
    {
    }

    @Override
    public Supplier toDomain(SupplierJpa object) {
        return new Supplier(UUID.fromString(object.getUuid()), object.getCompanyName(), object.getCountry() );
    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public SupplierJpa fromDomain(Supplier object) {

        SupplierJpa supplierJpa = new SupplierJpa(
                object.getUuid().toString(),
                object.getCompanyName(),
                object.getCountry()
        );
        return supplierJpa;
    }
}

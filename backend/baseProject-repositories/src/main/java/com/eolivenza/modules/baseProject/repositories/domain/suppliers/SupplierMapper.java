package com.eolivenza.modules.baseProject.repositories.domain.suppliers;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.categories.Category;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import com.eolivenza.modules.baseProject.repositories.domain.categories.CategoriesMapper;
import com.eolivenza.modules.baseProject.repositories.domain.categories.CategoryJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.ProductJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

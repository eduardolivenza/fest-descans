package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper implements Mapper<ProductType, ProductTypeJpa> {
    /**
     * Creates the reagentConsumer domain object out of the JPA reagentConsumer object
     *
     * @param object the JPA reagentConsumer object
     * @return the reagentConsumer domain object
     */
    @Override
    public ProductType toDomain(ProductTypeJpa object) {
        return new ProductType(
                object.getProductName());
    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public ProductTypeJpa fromDomain(ProductType object) {
        return new ProductTypeJpa(
                object.getUUID().toString(),
                object.getProductName()
        );
    }
}

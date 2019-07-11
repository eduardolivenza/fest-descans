package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductJpa> {

    @Override
    public Product toDomain(ProductJpa object) {
        return new Product()
                .setEmail( object.getEmail())
                .setName( object.getName())

                .setPassword(object.getPassword());

    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public ProductJpa fromDomain(Product object) {
        return new ProductJpa(
                object.getEmail(),
                object.getName(),
                object.getPassword()
        );
    }
}

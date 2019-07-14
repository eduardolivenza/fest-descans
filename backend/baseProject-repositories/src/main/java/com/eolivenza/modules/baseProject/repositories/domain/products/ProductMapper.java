package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<Product, ProductJpa> {

    AvailableProductSizeMapper availableProductSizeMapper;

    public ProductMapper(AvailableProductSizeMapper availableProductSizeMapper)
    {
        this.availableProductSizeMapper = availableProductSizeMapper;
    }

    @Override
    public Product toDomain(ProductJpa object) {
        Set<AvailableProduct> availableProducts = object.productSizes.stream().map(availableProductSizeMapper::toDomain).collect(Collectors.toSet());

        return new Product(UUID.fromString(object.getUuid()), object.getProductIdentifier(), availableProducts );
    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public ProductJpa fromDomain(Product object) {
        HashSet<AvailableProductSizeJpa> sizesList = new HashSet<AvailableProductSizeJpa>();
        ProductJpa productJpa = new ProductJpa(
                object.getUuid().toString(),
                object.getProductIdentifier(),
                sizesList
        );
        for (AvailableProduct size : object.getAvailableProducts())
        {
            AvailableProductSizeJpa availableProduct = this.availableProductSizeMapper.fromDomain(size);
            availableProduct.productJpa = productJpa;
            sizesList.add(availableProduct);
        }
        return productJpa;
    }
}

package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ProductsResourceMapper implements ResourceMapper<Product, ProductResource> {

    private final AvailableProductResourceMapper availableProductResourceMapper;

    public ProductsResourceMapper(AvailableProductResourceMapper availableProductResourceMapper) {
        this.availableProductResourceMapper = availableProductResourceMapper;
    }

    @Override
    public Product toFirstType(ProductResource object) {
        return new Product(
                object.identifier,
                new HashSet<AvailableProduct>());
    }

    @Override
    public ProductResource toSecondType(Product object) {
        return new ProductResource(object.getUuid().toString(), object.getProductIdentifier(),  object.getAvailableProducts().stream().map(availableProductResourceMapper::toSecondType).collect(Collectors.toSet()));
    }
}

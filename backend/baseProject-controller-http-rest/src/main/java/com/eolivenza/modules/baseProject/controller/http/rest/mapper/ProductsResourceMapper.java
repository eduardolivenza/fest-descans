package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
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
                Category.valueOf(object.category),
                object.identifier,
                object.productDescription,
                object.comfortLevel,
                null,
                new HashSet<AvailableProduct>());
    }

    @Override
    public ProductResource toSecondType(Product object) {
        return new ProductResource(object.getUuid().toString(), object.getCategory().name(), object.getProductIdentifier(), object.getDescription(), object.getComfortLevel(), object.getSupplier().getCompanyName(), object.getAvailableProducts().stream().map(availableProductResourceMapper::toSecondType).collect(Collectors.toSet()));
    }
}

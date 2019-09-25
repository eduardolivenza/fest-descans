package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.AvailableSizeResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.products.ProductImage;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class ProductsResourceMapper implements ResourceMapper<Product, ProductResource> {

    private final AvailableProductResourceMapper availableProductResourceMapper;
    private final SupplierResourceMapper supplierResourceMapper;
    private final ImageResourceMapper imageResourceMapper;

    public ProductsResourceMapper(AvailableProductResourceMapper availableProductResourceMapper, SupplierResourceMapper supplierResourceMapper, ImageResourceMapper imageResourceMapper) {
        this.availableProductResourceMapper = availableProductResourceMapper;
        this.supplierResourceMapper = supplierResourceMapper;
        this.imageResourceMapper = imageResourceMapper;
    }

    @Override
    public Product toFirstType(ProductResource object) {
        return new Product(
            Category.valueOf(object.category),
            object.identifier,
            object.productName,
            object.productDescription,
            object.comfortLevel,
            null,
            new HashSet<AvailableProduct>(),
            new HashSet<ProductImage>());
    }

    @Override
    public ProductResource toSecondType(Product object) {
        return new ProductResource(
                object.getUuid().toString(),
                object.getCategory().name(),
                object.getProductIdentifier(),
                object.getProductName(),
                object.getDescription(), object.getComfortLevel(),
                supplierResourceMapper.toSecondType(object.getSupplier()),
                object.getAvailableProducts().stream().map(availableProductResourceMapper::toSecondType).collect(Collectors.toSet()),
                object.getProductImages().stream().map(imageResourceMapper::toSecondType).collect(Collectors.toSet())
        );
    }
}

package com.eolivenza.modules.baseProject.repositories.domain.products.productImages;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper implements Mapper<ProductImage, ProductImageJpa> {

    private final ProductImageJpaSpringData productImageJpaSpringData;

    @Autowired
    public ProductImageMapper(ProductImageJpaSpringData productImageJpaSpringData) {
        this.productImageJpaSpringData = productImageJpaSpringData;
    }

    @Override
    public ProductImage toDomain(ProductImageJpa object) {
        ProductImage domainProduct = new ProductImage(object.getIdentifier(), object.getFilename());
        return domainProduct;
    }

    @Override
    public ProductImageJpa fromDomain(ProductImage object) {
        ProductImageJpa productImageJpa = new ProductImageJpa(object.getIdentifier(), object.getFilename());
        return productImageJpa;
    }

}

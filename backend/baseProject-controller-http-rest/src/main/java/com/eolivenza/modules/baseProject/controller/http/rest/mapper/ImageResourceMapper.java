package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.domain.model.products.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ImageResourceMapper implements ResourceMapper<ProductImage, String> {

    @Override
    public ProductImage toFirstType(String filename) {
        return new ProductImage(filename);
    }

    @Override
    public String toSecondType(ProductImage object) {
        return object.getFilename();
    }
}

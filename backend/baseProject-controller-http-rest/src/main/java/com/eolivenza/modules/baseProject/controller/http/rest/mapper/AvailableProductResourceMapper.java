package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.AvailableSizeResource;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import org.springframework.stereotype.Component;

@Component
public class AvailableProductResourceMapper implements ResourceMapper<AvailableProduct, AvailableSizeResource> {

    @Override
    public AvailableProduct toFirstType(AvailableSizeResource object) {
        return new AvailableProduct(
                object.size,
                object.price
               );
    }

    @Override
    public AvailableSizeResource toSecondType(AvailableProduct object) {
        return new AvailableSizeResource(object.size, object.price);
    }
}

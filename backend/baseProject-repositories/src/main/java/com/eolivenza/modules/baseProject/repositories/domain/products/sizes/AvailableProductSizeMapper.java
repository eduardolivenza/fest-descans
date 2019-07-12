package com.eolivenza.modules.baseProject.repositories.domain.products.sizes;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AvailableProductSizeMapper implements Mapper<AvailableProduct, AvailableProductSizeJpa> {

    private final AvailableProductJpaSpringData availableProductJpaSpringData;


    @Autowired
    public AvailableProductSizeMapper(AvailableProductJpaSpringData availableProductJpaSpringData) {
        this.availableProductJpaSpringData = availableProductJpaSpringData;
    }

    @Override
    public AvailableProduct toDomain(AvailableProductSizeJpa object) {
        AvailableProduct domainProduct = new AvailableProduct(object.getIdentifier(), object.getSize(), object.getValue());
        return domainProduct;
    }

    @Override
    public AvailableProductSizeJpa fromDomain(AvailableProduct object) {
        AvailableProductSizeJpa alarmJpaOptional = new AvailableProductSizeJpa(object.identifier, object.size, object.price);

        return alarmJpaOptional;
    }

    /*
    public AvailableProductSizeJpa fromDomain(Product reagentConsumer, AvailableProduct value) {
        List<AvailableProductSizeJpa> alarmJpaList = availableProductJpaSpringData.findAllByValue(value.identifier);
        Optional<AvailableProductSizeJpa> optionalAlarmJpa = alarmJpaList.stream()
                .filter(alarmJpa -> alarmJpa.productJpa.getUuid().equals(reagentConsumer.getUuid().toString())).findFirst();
        return optionalAlarmJpa.orElseGet(() -> new AvailableProductSizeJpa(value.size, value.price));
    }*/

}

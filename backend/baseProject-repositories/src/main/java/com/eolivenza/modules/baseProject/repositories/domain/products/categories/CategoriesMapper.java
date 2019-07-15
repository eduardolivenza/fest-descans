package com.eolivenza.modules.baseProject.repositories.domain.products.categories;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductJpaSpringData;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CategoriesMapper implements Mapper<Category, CategoryJpa> {

    private final CategoriesJpaSpringData categoriesJpaSpringData;


    @Autowired
    public CategoriesMapper(CategoriesJpaSpringData categoriesJpaSpringData) {
        this.categoriesJpaSpringData = categoriesJpaSpringData;
    }

    @Override
    public Category toDomain(CategoryJpa object) {
        Category domainProduct = new Category(object.getIdentifier(), object.getDescription());
        return domainProduct;
    }

    @Override
    public CategoryJpa fromDomain(Category object) {

        CategoryJpa categoryJpa = new CategoryJpa(object.getIdentifier(), object.getDescription(), new HashSet<>());

        return categoryJpa;
    }

    /*
    public AvailableProductSizeJpa fromDomain(Product reagentConsumer, AvailableProduct value) {
        List<AvailableProductSizeJpa> alarmJpaList = availableProductJpaSpringData.findAllByValue(value.identifier);
        Optional<AvailableProductSizeJpa> optionalAlarmJpa = alarmJpaList.stream()
                .filter(alarmJpa -> alarmJpa.productJpa.getUuid().equals(reagentConsumer.getUuid().toString())).findFirst();
        return optionalAlarmJpa.orElseGet(() -> new AvailableProductSizeJpa(value.size, value.price));
    }*/

}

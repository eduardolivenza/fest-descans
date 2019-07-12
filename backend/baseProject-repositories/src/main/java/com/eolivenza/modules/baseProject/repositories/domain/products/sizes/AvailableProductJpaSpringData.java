package com.eolivenza.modules.baseProject.repositories.domain.products.sizes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AvailableProductJpaSpringData extends CrudRepository<AvailableProductSizeJpa, String> {
    List<AvailableProductSizeJpa> findAllByValue(String value);
}


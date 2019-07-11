package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;

import java.util.UUID;

public interface ProductTypeRepository extends Repository<ProductType, UUID>, RetrieveRepository<ProductType, UUID> {

    boolean exists(UUID uuid);
}

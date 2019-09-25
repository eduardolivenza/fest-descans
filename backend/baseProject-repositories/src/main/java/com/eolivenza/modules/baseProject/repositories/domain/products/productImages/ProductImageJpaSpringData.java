package com.eolivenza.modules.baseProject.repositories.domain.products.productImages;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductImageJpaSpringData extends CrudRepository<ProductImageJpa, String> {

}


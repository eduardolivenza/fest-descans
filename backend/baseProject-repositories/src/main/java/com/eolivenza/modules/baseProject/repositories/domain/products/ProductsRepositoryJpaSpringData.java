package com.eolivenza.modules.baseProject.repositories.domain.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepositoryJpaSpringData extends JpaRepository<ProductJpa, String> {

    boolean existsByProductName(String productName);

}

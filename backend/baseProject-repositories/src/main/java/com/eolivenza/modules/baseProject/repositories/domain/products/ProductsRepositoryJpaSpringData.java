package com.eolivenza.modules.baseProject.repositories.domain.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductsRepositoryJpaSpringData extends JpaRepository<ProductJpa, String> {

    boolean existsByProductName(String productName);

    Optional<ProductJpa> findByProductName(String identifier);

}

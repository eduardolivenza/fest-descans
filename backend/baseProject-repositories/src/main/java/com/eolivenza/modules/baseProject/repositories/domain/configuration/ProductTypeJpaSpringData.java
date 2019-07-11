package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeJpaSpringData extends JpaRepository<ProductTypeJpa, String> {
}

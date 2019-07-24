package com.eolivenza.modules.baseProject.repositories.domain.suppliers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepositoryJpaSpringData extends JpaRepository<SupplierJpa, String> {




}

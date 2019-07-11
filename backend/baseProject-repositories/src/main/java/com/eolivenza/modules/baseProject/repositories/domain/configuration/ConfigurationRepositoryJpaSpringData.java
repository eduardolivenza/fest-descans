package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepositoryJpaSpringData extends JpaRepository<ConfigurationJpa, String> {
}

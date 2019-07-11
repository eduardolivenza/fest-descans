package com.eolivenza.modules.baseProject.configuration.persistence.jpa_repositories;

import com.eolivenza.modules.baseProject.application.TransactionManagerNames;
import com.eolivenza.modules.baseProject.configuration.persistence.databases.DomainDatabaseConfiguration;
import com.eolivenza.modules.baseProject.repositories.domain.DomainRepositoriesBasePackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = DomainRepositoriesBasePackage.class, entityManagerFactoryRef = DomainDatabaseConfiguration.DOMAIN_ENTITY_MANAGER_FACTORY_NAME, transactionManagerRef = TransactionManagerNames.DOMAIN)
public class DomainJpaRepositoriesConfiguration {
}

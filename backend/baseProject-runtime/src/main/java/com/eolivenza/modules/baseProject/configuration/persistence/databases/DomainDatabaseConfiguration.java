package com.eolivenza.modules.baseProject.configuration.persistence.databases;

import com.eolivenza.modules.baseProject.application.TransactionManagerNames;
import com.eolivenza.modules.baseProject.configuration.ProfileNames;
import com.eolivenza.modules.baseProject.configuration.persistence.datasources.DomainDataSourceConfiguration;
import com.eolivenza.modules.baseProject.properties.hibernate.*;
import com.eolivenza.modules.baseProject.properties.jpa.JavaxPersistenceSchemaGenerationDatabaseAction;
import com.eolivenza.modules.baseProject.repositories.domain.DomainRepositoriesBasePackage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DomainDatabaseConfiguration {
    public static final String DOMAIN_ENTITY_MANAGER_FACTORY_NAME = "domainEntityManagerFactory";
    private static final String DOMAIN_PERSISTENCE_UNIT_NAME = "domainPersistenceUnit";
    private static final String DOMAIN_JPA_PROPERTIES_NAME = "domainJpaProperties";

    @Profile(ProfileNames.DEVELOPMENT)
    @Bean(DOMAIN_JPA_PROPERTIES_NAME)
    public Map<String, Object> jpaPropertiesDevelopment() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(JavaxPersistenceSchemaGenerationDatabaseAction.KEY, JavaxPersistenceSchemaGenerationDatabaseAction.DROP_AND_CREATE.getValue());
        properties.put(HibernateShowSql.KEY, HibernateShowSql.TRUE.getValue());
        properties.put(HibernateDialect.KEY, "org.hibernate.dialect.H2Dialect");
        properties.put(HibernateFormatSql.KEY, HibernateFormatSql.TRUE.getValue());
        properties.put(HibernateUseSqlComments.KEY, HibernateUseSqlComments.TRUE.getValue());
        properties.put(HibernateHqlBulkIdStrategy.KEY, HibernateHqlBulkIdStrategy.INILINE_IDS_OR_BULK_IDS.getValue());
        return properties;
    }

    @Profile(ProfileNames.CONTROLLER_TO_SECONDARY_ADAPTERS_WITH_IN_MEMORY_H2)
    @Bean(DOMAIN_JPA_PROPERTIES_NAME)
    public Map<String, Object> jpaPropertiesControllerToSecondaryAdaptersWithInMemoryH2() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(JavaxPersistenceSchemaGenerationDatabaseAction.KEY, JavaxPersistenceSchemaGenerationDatabaseAction.CREATE.getValue());
        properties.put(HibernateDialect.KEY, "org.hibernate.dialect.H2Dialect");
        properties.put(HibernateHqlBulkIdStrategy.KEY, HibernateHqlBulkIdStrategy.INILINE_IDS_OR_BULK_IDS.getValue());
        return properties;
    }

    @Profile({ ProfileNames.PRODUCTION, ProfileNames.DEBUG })
    @Bean(DOMAIN_JPA_PROPERTIES_NAME)
    public Map<String, Object> jpaPropertiesProduction() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(JavaxPersistenceSchemaGenerationDatabaseAction.KEY, JavaxPersistenceSchemaGenerationDatabaseAction.NONE.getValue());
        properties.put(HibernateDialect.KEY, HibernateDialect.CUSTOM_CACHE71_DIALECT);
        properties.put(HibernateHqlBulkIdStrategy.KEY, HibernateHqlBulkIdStrategy.INILINE_IDS_OR_BULK_IDS.getValue());
        return properties;
    }

    @Bean(DOMAIN_ENTITY_MANAGER_FACTORY_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(DomainDataSourceConfiguration.DOMAIN_DATA_SOURCE_NAME) DataSource dataSource, @Value("#{" + DOMAIN_JPA_PROPERTIES_NAME + "}") Map<String, Object> properties) {
        return builder
                .dataSource(dataSource)
                .packages(DomainRepositoriesBasePackage.class)
                .persistenceUnit(DOMAIN_PERSISTENCE_UNIT_NAME)
                .properties(properties)
                .build();
    }

    @Bean(TransactionManagerNames.DOMAIN)
    public PlatformTransactionManager transactionManager(@Qualifier(DOMAIN_ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}

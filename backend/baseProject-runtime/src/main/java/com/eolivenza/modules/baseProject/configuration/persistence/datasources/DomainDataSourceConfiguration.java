package com.eolivenza.modules.baseProject.configuration.persistence.datasources;

import com.eolivenza.modules.baseProject.properties.ApplicationPropertyPrefixes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DomainDataSourceConfiguration {
    private static final String DOMAIN_DATA_SOURCE_PROPERTIES_NAME = "domainDataSourceProperties";
    public static final String DOMAIN_DATA_SOURCE_NAME = "domainDataSource";

    @Bean(DOMAIN_DATA_SOURCE_PROPERTIES_NAME)
    @ConfigurationProperties(ApplicationPropertyPrefixes.DOMAIN_DATA_SOURCE)
    @Primary
    public DataSourceProperties domainAcbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(DOMAIN_DATA_SOURCE_NAME)
    @ConfigurationProperties(ApplicationPropertyPrefixes.DOMAIN_DATA_SOURCE)
    public DataSource domainAcbDataSource(@Qualifier(DOMAIN_DATA_SOURCE_PROPERTIES_NAME) DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}

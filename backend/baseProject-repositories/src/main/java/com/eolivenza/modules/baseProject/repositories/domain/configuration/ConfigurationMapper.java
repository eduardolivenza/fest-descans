package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationMapper implements Mapper<Configuration, ConfigurationJpa> {
    /**
     * Creates the reagentConsumer domain object out of the JPA reagentConsumer object
     *
     * @param object the JPA reagentConsumer object
     * @return the reagentConsumer domain object
     */
    @Override
    public Configuration toDomain(ConfigurationJpa object) {
        return new Configuration(
                object.getClientIdentifier(),
                object.getExportPath(),
                object.getCountryIdentifier(),
                object.getDemographicIdentifier(),
                object.isAutomaticExportEnabled(),
                object.getLocalExecutionTime(),
                object.getReportFrequency(),
                object.getDayOfWeek(),
                object.getMonthDay());
    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public ConfigurationJpa fromDomain(Configuration object) {
        return new ConfigurationJpa(
                object.getUUID().toString(),
                object.getClientIdentifier(),
                object.getExportPath(),
                object.getCountryIdentifier(),
                object.getDemographicIdentifier(),
                object.isAutomaticExportEnabled(),
                object.getLocalExecutionTime(),
                object.getReportFrequency(),
                object.getDayOfWeek(),
                object.getMonthDay()
        );
    }
}

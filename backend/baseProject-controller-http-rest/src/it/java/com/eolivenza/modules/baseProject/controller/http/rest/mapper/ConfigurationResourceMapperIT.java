package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResourceDataBuilder;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.configuration.ConfigurationDataBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationResourceMapperIT {

    private ConfigurationResourceMapper configurationResourceMapper;

    @Before
    public void setUp() {
        this.configurationResourceMapper = new ConfigurationResourceMapper();
    }

    @Test
    public void fromDomainToResource() {
        Configuration configuration = ConfigurationDataBuilder
                .defaultWithAutomaticExportEnabledBuilder()
                .build();

        ConfigurationResource configurationResource = configurationResourceMapper.toSecondType(configuration);

        assertThat(configurationResource.uuid).isEqualTo(configuration.getUUID().toString());
        assertThat(configurationResource.clientIdentifier).isEqualTo(configuration.getClientIdentifier());
        assertThat(configurationResource.exportPath).isEqualTo(configuration.getExportPath());
        assertThat(configurationResource.countryIdentifier).isEqualTo(configuration.getCountryIdentifier());
        assertThat(configurationResource.demographicIdentifier).isEqualTo(configuration.getDemographicIdentifier());
        assertThat(configurationResource.automaticExportEnabled).isEqualTo(configuration.isAutomaticExportEnabled());
        assertThat(configurationResource.localExecutionTime).isEqualTo(configuration.getLocalExecutionTime().toString());
        assertThat(configurationResource.reportFrequency).isEqualTo(configuration.getReportFrequency().toString());
        assertThat(configurationResource.dayOfWeek).isEqualTo(configuration.getDayOfWeek().toString());
        assertThat(configurationResource.monthDay).isEqualTo(configuration.getMonthDay());
    }

    @Test
    public void fromResourceToDomain() {
        ConfigurationResource configurationResource = ConfigurationResourceDataBuilder
                .defaultBuilder()
                .build();

        Configuration configuration = configurationResourceMapper.toFirstType(configurationResource);

        assertThat(configurationResource.clientIdentifier).isEqualTo(configuration.getClientIdentifier());
        assertThat(configurationResource.exportPath).isEqualTo(configuration.getExportPath());
        assertThat(configurationResource.countryIdentifier).isEqualTo(configuration.getCountryIdentifier());
        assertThat(configurationResource.demographicIdentifier).isEqualTo(configuration.getDemographicIdentifier());
        assertThat(configurationResource.automaticExportEnabled).isEqualTo(configuration.isAutomaticExportEnabled());
        assertThat(configurationResource.localExecutionTime).isEqualTo(configuration.getLocalExecutionTime().toString());
        assertThat(configurationResource.reportFrequency).isEqualTo(configuration.getReportFrequency().toString());
        assertThat(configurationResource.dayOfWeek).isEqualTo(configuration.getDayOfWeek().toString());
        assertThat(configurationResource.monthDay).isEqualTo(configuration.getMonthDay());
    }
}
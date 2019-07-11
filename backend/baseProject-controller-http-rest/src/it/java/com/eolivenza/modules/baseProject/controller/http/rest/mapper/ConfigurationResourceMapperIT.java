package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResourceDataBuilder;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
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
        ProductType productType = ConfigurationDataBuilder
                .defaultWithAutomaticExportEnabledBuilder()
                .build();

        ConfigurationResource configurationResource = configurationResourceMapper.toSecondType(productType);

        assertThat(configurationResource.uuid).isEqualTo(productType.getUUID().toString());
        assertThat(configurationResource.clientIdentifier).isEqualTo(productType.getClientIdentifier());
        assertThat(configurationResource.exportPath).isEqualTo(productType.getExportPath());
        assertThat(configurationResource.countryIdentifier).isEqualTo(productType.getCountryIdentifier());
        assertThat(configurationResource.demographicIdentifier).isEqualTo(productType.getDemographicIdentifier());
        assertThat(configurationResource.automaticExportEnabled).isEqualTo(productType.isAutomaticExportEnabled());
        assertThat(configurationResource.localExecutionTime).isEqualTo(productType.getLocalExecutionTime().toString());
        assertThat(configurationResource.reportFrequency).isEqualTo(productType.getReportFrequency().toString());
        assertThat(configurationResource.dayOfWeek).isEqualTo(productType.getDayOfWeek().toString());
        assertThat(configurationResource.monthDay).isEqualTo(productType.getMonthDay());
    }

    @Test
    public void fromResourceToDomain() {
        ConfigurationResource configurationResource = ConfigurationResourceDataBuilder
                .defaultBuilder()
                .build();

        ProductType productType = configurationResourceMapper.toFirstType(configurationResource);

        assertThat(configurationResource.clientIdentifier).isEqualTo(productType.getClientIdentifier());
        assertThat(configurationResource.exportPath).isEqualTo(productType.getExportPath());
        assertThat(configurationResource.countryIdentifier).isEqualTo(productType.getCountryIdentifier());
        assertThat(configurationResource.demographicIdentifier).isEqualTo(productType.getDemographicIdentifier());
        assertThat(configurationResource.automaticExportEnabled).isEqualTo(productType.isAutomaticExportEnabled());
        assertThat(configurationResource.localExecutionTime).isEqualTo(productType.getLocalExecutionTime().toString());
        assertThat(configurationResource.reportFrequency).isEqualTo(productType.getReportFrequency().toString());
        assertThat(configurationResource.dayOfWeek).isEqualTo(productType.getDayOfWeek().toString());
        assertThat(configurationResource.monthDay).isEqualTo(productType.getMonthDay());
    }
}
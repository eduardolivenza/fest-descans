package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.repositories.domain.ITConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = ConfigurationMapper.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class ConfigurationMapperIT {

    private ConfigurationMapper configurationMapper;

    @Before
    public void setUp() {
        configurationMapper = new ConfigurationMapper();
    }

    @Test
    public void toDomain() {
        ConfigurationJpa configurationJpa = new ConfigurationJpa();

        configurationJpa.uuid = Configuration.CONFIGURATION_UUID.toString();
        configurationJpa.setClientIdentifier("clientID");
        configurationJpa.setCountryIdentifier("UK");
        configurationJpa.setDemographicIdentifier(2);
        configurationJpa.setAutomaticExportEnabled( true);
        configurationJpa.setLocalExecutionTime(LocalTime.NOON);
        configurationJpa.setExportPath("d:/");
        configurationJpa.setMonthDay(16);
        configurationJpa.setDayOfWeek(null);
        configurationJpa.setReportFrequency(Configuration.ReportFrequency.MONTHLY);

        Configuration configuration = configurationMapper.toDomain(configurationJpa);

        assertThat(configurationJpa.uuid).isEqualTo(configuration.getUUID().toString());
        assertThat(configurationJpa.getClientIdentifier()).isEqualTo(configuration.getClientIdentifier());
        assertThat(configurationJpa.getCountryIdentifier()).isEqualTo(configuration.getCountryIdentifier());
        assertThat(configurationJpa.getDayOfWeek()).isEqualTo(configuration.getDayOfWeek());
        assertThat(configurationJpa.getDemographicIdentifier()).isEqualTo(configuration.getDemographicIdentifier());
        assertThat(configurationJpa.isAutomaticExportEnabled()).isEqualTo(configuration.isAutomaticExportEnabled());
        assertThat(configurationJpa.getLocalExecutionTime()).isEqualTo(configuration.getLocalExecutionTime());
        assertThat(configurationJpa.getExportPath()).isEqualTo(configuration.getExportPath());
        assertThat(configurationJpa.getMonthDay()).isEqualTo(configuration.getMonthDay());
        assertThat(configurationJpa.getReportFrequency()).isEqualTo(configuration.getReportFrequency());
    }
}
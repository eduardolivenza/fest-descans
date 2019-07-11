package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.configuration.ConfigurationDataBuilder;
import com.eolivenza.modules.baseProject.repositories.domain.ITConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = ConfigurationRepositoryImpl.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class ConfigurationRepositoryImplIT {
    @Autowired
    private ConfigurationRepositoryImpl configurationRepositoryImpl;

    @Test
    public void create() {
        configurationRepositoryImpl.create(ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build());

        assertThat(configurationRepositoryImpl.exists(Configuration.CONFIGURATION_UUID)).isTrue();
    }

    @Test
    public void retrieve_new_configuration() {
        Configuration retrievedConfiguration = configurationRepositoryImpl.retrieve(Configuration.CONFIGURATION_UUID);

        assertThat(retrievedConfiguration).isNull();
    }

    @Test
    public void retrieve_existing_configuration() {
        Configuration configuration = ConfigurationDataBuilder
                .defaultBuilder()
                .build();
        configurationRepositoryImpl.create(configuration);

        Configuration retrievedConfiguration = configurationRepositoryImpl.retrieve(configuration.getUUID());

        assertThat(retrievedConfiguration.getUUID()).isEqualTo(configuration.getUUID());
    }

    @Test
    public void update() {
        Configuration originalConfiguration = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();
        originalConfiguration = configurationRepositoryImpl.create(originalConfiguration);

        String newClientIdentifier = "new" + originalConfiguration.getClientIdentifier();
        originalConfiguration.setClientIdentifier(newClientIdentifier);

        Configuration updatedConfiguration = configurationRepositoryImpl.update(originalConfiguration);

        assertThat(updatedConfiguration.getClientIdentifier()).isEqualTo(newClientIdentifier);
    }

    @Test
    public void delete() {
        Configuration configuration = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();
        configurationRepositoryImpl.create(configuration);

        configurationRepositoryImpl.delete(configuration.getUUID());

        assertThat(configurationRepositoryImpl.exists(configuration.getUUID())).isFalse();
    }
}
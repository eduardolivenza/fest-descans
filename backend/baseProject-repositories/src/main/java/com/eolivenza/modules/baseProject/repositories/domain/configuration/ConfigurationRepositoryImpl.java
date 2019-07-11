package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public class ConfigurationRepositoryImpl implements ConfigurationRepository {
    private final ConfigurationMapper configurationMapper;

    private final ConfigurationRepositoryJpaSpringData configurationRepositoryJpaSpringData;

    @Autowired
    public ConfigurationRepositoryImpl(ConfigurationMapper configurationMapper, ConfigurationRepositoryJpaSpringData configurationRepositoryJpaSpringData) {
        this.configurationMapper = configurationMapper;
        this.configurationRepositoryJpaSpringData = configurationRepositoryJpaSpringData;
    }

    @Override
    public Configuration update(Configuration entity) {
        ConfigurationJpa configurationJpa = configurationMapper.fromDomain(entity);
        configurationJpa = configurationRepositoryJpaSpringData.saveAndFlush(configurationJpa);

        return configurationMapper.toDomain(configurationJpa);
    }

    @Override
    public Configuration retrieve(UUID identity) {
        Configuration configuration = null;

        Optional<ConfigurationJpa> optionalConfigurationJPA =
                configurationRepositoryJpaSpringData.findById(Configuration.CONFIGURATION_UUID.toString());

        if (optionalConfigurationJPA.isPresent())
            configuration = configurationMapper.toDomain(optionalConfigurationJPA.get());

        return configuration;
    }

    @Override
    public boolean exists(UUID uuid) {
        return configurationRepositoryJpaSpringData.existsById(uuid.toString());
    }

    @Override
    public Configuration create(Configuration entity) {
        ConfigurationJpa configurationJpa = configurationMapper.fromDomain(entity);
        configurationJpa = configurationRepositoryJpaSpringData.saveAndFlush(configurationJpa);
        return configurationMapper.toDomain(configurationJpa);
    }

    @Override
    public void delete(UUID identity) {
        configurationRepositoryJpaSpringData.deleteById(identity.toString());
    }
}

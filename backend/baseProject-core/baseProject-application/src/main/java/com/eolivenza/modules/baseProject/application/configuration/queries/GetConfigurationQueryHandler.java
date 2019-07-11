package com.eolivenza.modules.baseProject.application.configuration.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class GetConfigurationQueryHandler implements QueryHandler<Class<Void>, Optional<Configuration>> {
    private final ConfigurationRepository configurationRepository;

    @Inject
    public GetConfigurationQueryHandler(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    /**
     * Retrieve the {@link Configuration}
     *
     * @param getConfigurationRequest void class
     * @return a {@link Configuration}
     **/
    @DomainStrictTransactional
    @Override
    public Optional<Configuration> apply(Class<Void> getConfigurationRequest) {
        return Optional.ofNullable(configurationRepository.retrieve(Configuration.CONFIGURATION_UUID));
    }

    @Override
    public String getName() { return "GetConfiguration"; }


}

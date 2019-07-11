package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import java.util.UUID;

public interface ConfigurationRepository extends Repository<Configuration, UUID>, RetrieveRepository<Configuration, UUID> {

    boolean exists(UUID uuid);
}

package com.eolivenza.modules.baseProject.configuration;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.QueryLogger;
import com.eolivenza.modules.baseProject.application.configuration.queries.GetConfigurationQueryHandler;
import com.eolivenza.modules.baseProject.application.users.queries.GetUserQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

@Configuration
public class QueryHandlerConfiguration {

    @Bean
    @Primary
    public QueryHandler<Class<Void>, Optional<com.eolivenza.modules.baseProject.domain.model.configuration.Configuration>> GetConfigurationQueryHandler(GetConfigurationQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<Class<String>, Optional<com.eolivenza.modules.baseProject.domain.model.user.User>> User(GetUserQueryHandler handler) {
        return new QueryLogger(handler);
    }

}

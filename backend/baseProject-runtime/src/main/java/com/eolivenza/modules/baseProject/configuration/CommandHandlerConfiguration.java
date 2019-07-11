package com.eolivenza.modules.baseProject.configuration;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.CommandLogger;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommand;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommandHandlerConfiguration {

    @Bean
    @Primary
    public CommandHandler<OverwriteConfigurationCommand> overwriteConfigurationCommand(OverwriteConfigurationCommandHandler handler) {
        return new CommandLogger<>(handler);
    }


}

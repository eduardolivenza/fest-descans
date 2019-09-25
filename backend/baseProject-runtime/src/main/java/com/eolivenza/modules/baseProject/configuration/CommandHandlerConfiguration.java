package com.eolivenza.modules.baseProject.configuration;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.CommandLogger;
import com.eolivenza.modules.baseProject.application.images.commands.StoreImageCommand;
import com.eolivenza.modules.baseProject.application.images.commands.StoreImageCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommandHandlerConfiguration {

    @Bean
    @Primary
    public CommandHandler<StoreImageCommand> storeImageCommand(StoreImageCommandHandler handler) {
        return new CommandLogger<>(handler);
    }


}

package com.eolivenza.modules.baseProject.configuration;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.CommandLogger;
import com.eolivenza.modules.baseProject.application.products.commands.productImages.AddProductImageCommand;
import com.eolivenza.modules.baseProject.application.products.commands.productImages.AddProductImageCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommandHandlerConfiguration {

    @Bean
    @Primary
    public CommandHandler<AddProductImageCommand> storeImageCommand(AddProductImageCommandHandler handler) {
        return new CommandLogger<>(handler);
    }


}

package com.eolivenza.modules.baseProject.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandLogger<CommandType> implements CommandHandler<CommandType> {

    private Logger logger = LoggerFactory.getLogger(CommandLogger.class);
    private CommandHandler<CommandType> commandHandler;

    public CommandLogger(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public CommandLogger(CommandHandler commandHandler, Logger logger) {
        this.commandHandler = commandHandler;
        this.logger = logger;
    }

    public void accept(CommandType command) {
        if (logger.isDebugEnabled()) {
            logger.debug("Starting command {}", commandHandler.getName());
        }
        commandHandler.accept(command);
        if (logger.isDebugEnabled()) {
            logger.debug("Completed command {}", commandHandler.getName());
        }
    }
}

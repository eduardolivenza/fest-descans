package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.configuration.FileNameNotValidException;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OverwriteConfigurationCommandHandler implements CommandHandler<OverwriteConfigurationCommand> {
    private final ConfigurationRepository configurationRepository;

    private Logger logger = LoggerFactory.getLogger(OverwriteConfigurationCommandHandler.class);

    @Inject
    public OverwriteConfigurationCommandHandler(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(OverwriteConfigurationCommand overwriteConfigurationCommand) {
        String exportPath = overwriteConfigurationCommand.getExportPath();
        logger.debug(" Selected path for configuration element is: {}", exportPath);
        if (overwriteConfigurationCommand.getClientIdentifier().matches(".*[/\n\r\t\0\f`?*\\<>|\":].*")) {
            throw new FileNameNotValidException(overwriteConfigurationCommand.getClientIdentifier());
        }
        Configuration configurationWithNewValues = toDomain(overwriteConfigurationCommand);
        logger.debug(" Element configuration validated");
        if (configurationRepository.exists(Configuration.CONFIGURATION_UUID)) {
            Configuration actualConfiguration = configurationRepository.retrieve(Configuration.CONFIGURATION_UUID);
            actualConfiguration.overwriteWith(configurationWithNewValues);
            configurationRepository.update(actualConfiguration);
        }
        else {
            configurationRepository.create(configurationWithNewValues);
        }
    }

    private Configuration toDomain(OverwriteConfigurationCommand overwriteConfigurationCommand) {
        return new Configuration(
                overwriteConfigurationCommand.getClientIdentifier(),
                overwriteConfigurationCommand.getExportPath(),
                overwriteConfigurationCommand.getCountryIdentifier(),
                overwriteConfigurationCommand.getDemographicIdentifier(),
                overwriteConfigurationCommand.isEnableAutomaticExport(),
                overwriteConfigurationCommand.getLocalExecutionTime(),
                overwriteConfigurationCommand.getReportFrequency(),
                overwriteConfigurationCommand.getDayOfWeek(),
                overwriteConfigurationCommand.getMonthDay());
    }

    @Override
    public String getName() { return "OverwriteConfiguration"; }

}



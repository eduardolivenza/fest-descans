package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommand;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommandHandler;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.ConfigurationResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResource;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.configuration.ConfigurationDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.DayOfWeek;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({OverwriteConfigurationCommand.class})
public class ConfigurationControllerTest {

    @Mock
    private QueryHandler<Class<Void>, Optional<Configuration>> getConfigurationQueryHandlerMock;

    @Mock
    private OverwriteConfigurationCommandHandler overwriteConfigurationCommandHandlerMock;

    private ConfigurationController configurationController;

    private ConfigurationResourceMapper configurationResourceMapper;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(OverwriteConfigurationCommand.class);

        MockitoAnnotations.initMocks(this);

        configurationResourceMapper = new ConfigurationResourceMapper();
        configurationController = new ConfigurationController(getConfigurationQueryHandlerMock, configurationResourceMapper, overwriteConfigurationCommandHandlerMock);
    }

    @Test
    public void retrieveConfiguration() {
        Configuration configuration = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();

        when(getConfigurationQueryHandlerMock.apply(any(Void.TYPE.getClass()))).thenReturn(Optional.of(configuration));

        configurationController.retrieveConfiguration();

        verify(getConfigurationQueryHandlerMock).apply(Void.TYPE);
    }

    @Test
    public void overwriteConfiguration() {
        ConfigurationResource configurationResource = new ConfigurationResource();

        configurationResource.clientIdentifier = "clientID";
        configurationResource.countryIdentifier = "countryID";
        configurationResource.exportPath = "d:/";
        configurationResource.demographicIdentifier = 1;
        configurationResource.automaticExportEnabled = true;
        configurationResource.localExecutionTime = "09:00";
        configurationResource.reportFrequency = Configuration.ReportFrequency.WEEKLY.toString();
        configurationResource.monthDay = null;
        configurationResource.dayOfWeek = DayOfWeek.SATURDAY.toString();

        configurationController.overwriteConfiguration(configurationResource);

        verify(overwriteConfigurationCommandHandlerMock).accept(any(OverwriteConfigurationCommand.class));
    }

}
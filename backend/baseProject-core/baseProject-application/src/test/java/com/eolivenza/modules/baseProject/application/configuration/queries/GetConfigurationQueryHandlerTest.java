package com.eolivenza.modules.baseProject.application.configuration.queries;

import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class GetConfigurationQueryHandlerTest {
    @Mock
    private ConfigurationRepository configurationRepositoryMock;

    @Mock
    private Configuration configurationMock;

    private GetConfigurationQueryHandler getConfigurationQueryHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        getConfigurationQueryHandler = new GetConfigurationQueryHandler(configurationRepositoryMock);
    }

    @Test
    public void apply() {
        when(configurationRepositoryMock.retrieve(Configuration.CONFIGURATION_UUID)).thenReturn(configurationMock);

        getConfigurationQueryHandler.apply(Void.TYPE);

        Mockito.verify(configurationRepositoryMock).retrieve(Configuration.CONFIGURATION_UUID);
    }
}
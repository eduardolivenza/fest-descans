package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;

import com.eolivenza.modules.baseProject.application.configuration.FileNameNotValidException;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OverwriteConfigurationCommandHandlerTest {
    @Mock
    private ConfigurationRepository configurationRepositoryMock;

    @Mock
    private Configuration configurationMock;

    private OverwriteConfigurationCommandHandler overwriteConfigurationCommandHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        overwriteConfigurationCommandHandler = new OverwriteConfigurationCommandHandler(configurationRepositoryMock);
    }


    @Test
    public void existing_configuration_is_overwritten() {
        String exportPath = "c:";
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().withExportPath(exportPath).build();

        when(configurationRepositoryMock.exists(Configuration.CONFIGURATION_UUID)).thenReturn(true);
        when(configurationRepositoryMock.retrieve(Configuration.CONFIGURATION_UUID)).thenReturn(configurationMock);

        overwriteConfigurationCommandHandler.accept(overwriteConfigurationCommand);


        Mockito.verify(configurationRepositoryMock).exists(Configuration.CONFIGURATION_UUID);
        Mockito.verify(configurationRepositoryMock).retrieve(Configuration.CONFIGURATION_UUID);
        Mockito.verify(configurationRepositoryMock).update(any(Configuration.class));
    }

    @Test
    public void existing_configuration_is_overwritten_withNonAllowedCharacters() {
        String configName = "MyConfig:";
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().
                withClientIdentifier(configName).
                build();

        when(configurationRepositoryMock.exists(Configuration.CONFIGURATION_UUID)).thenReturn(true);
        when(configurationRepositoryMock.retrieve(Configuration.CONFIGURATION_UUID)).thenReturn(configurationMock);

        assertThatExceptionOfType(FileNameNotValidException.class)
                .isThrownBy(() -> overwriteConfigurationCommandHandler.accept(overwriteConfigurationCommand))
                .satisfies(e -> assertThat(e.getName()).isEqualTo(configName));
        AssertionsForClassTypes.assertThatExceptionOfType(FileNameNotValidException.class);
    }

    @Test
    public void configuration_is_created() {
        String exportPath = "c:";
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().withExportPath(exportPath).build();

        when(configurationRepositoryMock.exists(Configuration.CONFIGURATION_UUID)).thenReturn(false);

        overwriteConfigurationCommandHandler.accept(overwriteConfigurationCommand);

        Mockito.verify(configurationRepositoryMock).exists(Configuration.CONFIGURATION_UUID);
        Mockito.verify(configurationRepositoryMock).create(any(Configuration.class));
    }

    @Test
    public void checkGetName(){
        assertThat(overwriteConfigurationCommandHandler.getName()).isEqualTo("OverwriteConfiguration");
    }

}

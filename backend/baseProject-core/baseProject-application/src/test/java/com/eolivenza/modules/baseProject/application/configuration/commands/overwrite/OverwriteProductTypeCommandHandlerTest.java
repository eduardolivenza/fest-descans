package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;

import com.eolivenza.modules.baseProject.application.configuration.FileNameNotValidException;
import com.eolivenza.modules.baseProject.application.repositories.ProductTypeRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
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

public class OverwriteProductTypeCommandHandlerTest {
    @Mock
    private ProductTypeRepository productTypeRepositoryMock;

    @Mock
    private ProductType productTypeMock;

    private OverwriteConfigurationCommandHandler overwriteConfigurationCommandHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        overwriteConfigurationCommandHandler = new OverwriteConfigurationCommandHandler(productTypeRepositoryMock);
    }


    @Test
    public void existing_configuration_is_overwritten() {
        String exportPath = "c:";
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().withExportPath(exportPath).build();

        when(productTypeRepositoryMock.exists(ProductType.CONFIGURATION_UUID)).thenReturn(true);
        when(productTypeRepositoryMock.retrieve(ProductType.CONFIGURATION_UUID)).thenReturn(productTypeMock);

        overwriteConfigurationCommandHandler.accept(overwriteConfigurationCommand);


        Mockito.verify(productTypeRepositoryMock).exists(ProductType.CONFIGURATION_UUID);
        Mockito.verify(productTypeRepositoryMock).retrieve(ProductType.CONFIGURATION_UUID);
        Mockito.verify(productTypeRepositoryMock).update(any(ProductType.class));
    }

    @Test
    public void existing_configuration_is_overwritten_withNonAllowedCharacters() {
        String configName = "MyConfig:";
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().
                withClientIdentifier(configName).
                build();

        when(productTypeRepositoryMock.exists(ProductType.CONFIGURATION_UUID)).thenReturn(true);
        when(productTypeRepositoryMock.retrieve(ProductType.CONFIGURATION_UUID)).thenReturn(productTypeMock);

        assertThatExceptionOfType(FileNameNotValidException.class)
                .isThrownBy(() -> overwriteConfigurationCommandHandler.accept(overwriteConfigurationCommand))
                .satisfies(e -> assertThat(e.getName()).isEqualTo(configName));
        AssertionsForClassTypes.assertThatExceptionOfType(FileNameNotValidException.class);
    }

    @Test
    public void configuration_is_created() {
        String exportPath = "c:";
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().withExportPath(exportPath).build();

        when(productTypeRepositoryMock.exists(ProductType.CONFIGURATION_UUID)).thenReturn(false);

        overwriteConfigurationCommandHandler.accept(overwriteConfigurationCommand);

        Mockito.verify(productTypeRepositoryMock).exists(ProductType.CONFIGURATION_UUID);
        Mockito.verify(productTypeRepositoryMock).create(any(ProductType.class));
    }

    @Test
    public void checkGetName(){
        assertThat(overwriteConfigurationCommandHandler.getName()).isEqualTo("OverwriteConfiguration");
    }

}

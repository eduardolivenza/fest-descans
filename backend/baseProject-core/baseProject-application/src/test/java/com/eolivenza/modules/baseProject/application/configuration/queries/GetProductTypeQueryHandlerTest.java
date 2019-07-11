package com.eolivenza.modules.baseProject.application.configuration.queries;

import com.eolivenza.modules.baseProject.application.repositories.ProductTypeRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class GetProductTypeQueryHandlerTest {
    @Mock
    private ProductTypeRepository productTypeRepositoryMock;

    @Mock
    private ProductType productTypeMock;

    private GetConfigurationQueryHandler getConfigurationQueryHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        getConfigurationQueryHandler = new GetConfigurationQueryHandler(productTypeRepositoryMock);
    }

    @Test
    public void apply() {
        when(productTypeRepositoryMock.retrieve(ProductType.CONFIGURATION_UUID)).thenReturn(productTypeMock);

        getConfigurationQueryHandler.apply(Void.TYPE);

        Mockito.verify(productTypeRepositoryMock).retrieve(ProductType.CONFIGURATION_UUID);
    }
}
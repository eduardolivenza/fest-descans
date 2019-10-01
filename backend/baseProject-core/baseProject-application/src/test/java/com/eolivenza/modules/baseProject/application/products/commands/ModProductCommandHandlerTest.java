package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.application.products.queries.ProductNotFoundException;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.application.repositories.SuppliersRepository;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModProductCommandHandlerTest {

    @Mock
    private ProductsRepository productsRepositoryMock;
    @Mock
    private SuppliersRepository suppliersRepositoryMock;
    @Mock
    private Logger logger = LoggerFactory.getLogger(ModProductCommandHandler.class);

    @Mock
    private Optional<Product> optionalProductMocked;

    @Mock
    private Product productMocked;
    @Mock
    private Supplier supplierMocked;

    private ModProductCommandHandler modProductCommandHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        modProductCommandHandler = new ModProductCommandHandler(productsRepositoryMock, suppliersRepositoryMock);
    }

    @Test
    public void an_instrument_is_overwritten() {
        String externalIdentifier = "externalIdentifier";

        ModProductCommand modProductCommand = new ModProductCommand(externalIdentifier,"productName", "SOFA", "productDescription", 3, supplierMocked, new HashSet<AvailableProduct>() );

        when(productsRepositoryMock.retrieveByProductIdentifier(externalIdentifier)).thenReturn(optionalProductMocked);
        when(optionalProductMocked.isPresent()).thenReturn(true);
        when(optionalProductMocked.get()).thenReturn(productMocked);
        when(productMocked.getUuid()).thenReturn(UUID.fromString("821928d3-fdb2-43c7-8ac2-f4e7d163feec"));

        modProductCommandHandler.accept(modProductCommand);

        verify(productsRepositoryMock).retrieveByProductIdentifier(externalIdentifier);
        verify(productsRepositoryMock).update(any(Product.class));
    }

}

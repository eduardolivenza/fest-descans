package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.application.products.ProductExistsException;
import com.eolivenza.modules.baseProject.application.products.ProductWithThisNameExistsException;
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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddProductCommandHandlerTest {

    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private SuppliersRepository suppliersRepository;
    @Mock
    private Logger logger = LoggerFactory.getLogger(AddProductCommandHandler.class);

    private AddProductCommandHandler addProductCommandHandler;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        addProductCommandHandler = new AddProductCommandHandler(productsRepository, suppliersRepository);
    }

    @Test
    public void addingAProduct() {
        String productName = "myProductName";
        Supplier dummySupplier = new Supplier("S1", "", "");
        AddProductCommand addProductCommand = new AddProductCommand( productName, "SOFA", "productDescription", 3, dummySupplier, new HashSet<>());
        addProductCommandHandler.accept(addProductCommand);

        verify(productsRepository).existsByProductName(productName);
        verify(productsRepository).create(any(Product.class));
        verifyNoMoreInteractions(productsRepository);
    }

    @Test
    public void addingAProductTwoTimesWithSameNameIsRejected() {
        String productName = "currentProductName";
        String expectedExceptionMessage = "Product with name: ".concat(productName).concat(" already exists");

        Supplier dummySupplier = new Supplier("S1", "", "");
        AddProductCommand addProductCommand = new AddProductCommand( productName, "SOFA", "productDescription", 3, dummySupplier, new HashSet<>());
        when(productsRepository.existsByProductName(productName)).thenReturn(true);

        assertThatExceptionOfType(ProductWithThisNameExistsException.class)
                .isThrownBy(() -> addProductCommandHandler.accept(addProductCommand))
                .satisfies(e -> assertThat(e.getMessage()).as("Exception message").isEqualTo(expectedExceptionMessage));
    }

}










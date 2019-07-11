package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import com.eolivenza.modules.baseProject.domain.model.configuration.ConfigurationDataBuilder;
import com.eolivenza.modules.baseProject.repositories.domain.ITConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = ProductTypeRepositoryImpl.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class ProductTypeRepositoryImplIT {
    @Autowired
    private ProductTypeRepositoryImpl productTypeRepositoryImpl;

    @Test
    public void create() {
        productTypeRepositoryImpl.create(ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build());

        assertThat(productTypeRepositoryImpl.exists(ProductType.CONFIGURATION_UUID)).isTrue();
    }

    @Test
    public void retrieve_new_configuration() {
        ProductType retrievedProductType = productTypeRepositoryImpl.retrieve(ProductType.CONFIGURATION_UUID);

        assertThat(retrievedProductType).isNull();
    }

    @Test
    public void retrieve_existing_configuration() {
        ProductType productType = ConfigurationDataBuilder
                .defaultBuilder()
                .build();
        productTypeRepositoryImpl.create(productType);

        ProductType retrievedProductType = productTypeRepositoryImpl.retrieve(productType.getUUID());

        assertThat(retrievedProductType.getUUID()).isEqualTo(productType.getUUID());
    }

    @Test
    public void update() {
        ProductType originalProductType = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();
        originalProductType = productTypeRepositoryImpl.create(originalProductType);

        String newClientIdentifier = "new" + originalProductType.getClientIdentifier();
        originalProductType.setClientIdentifier(newClientIdentifier);

        ProductType updatedProductType = productTypeRepositoryImpl.update(originalProductType);

        assertThat(updatedProductType.getClientIdentifier()).isEqualTo(newClientIdentifier);
    }

    @Test
    public void delete() {
        ProductType productType = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();
        productTypeRepositoryImpl.create(productType);

        productTypeRepositoryImpl.delete(productType.getUUID());

        assertThat(productTypeRepositoryImpl.exists(productType.getUUID())).isFalse();
    }
}
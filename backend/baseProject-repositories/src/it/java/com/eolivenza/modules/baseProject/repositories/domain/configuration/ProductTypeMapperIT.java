package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import com.eolivenza.modules.baseProject.repositories.domain.ITConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = ProductTypeMapper.class)
@ContextConfiguration(classes = ITConfiguration.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class ProductTypeMapperIT {

    private ProductTypeMapper productTypeMapper;

    @Before
    public void setUp() {
        productTypeMapper = new ProductTypeMapper();
    }

    @Test
    public void toDomain() {
        ProductTypeJpa configurationJpa = new ProductTypeJpa();

        configurationJpa.uuid = ProductType.CONFIGURATION_UUID.toString();
        configurationJpa.setProductName("productName");

        ProductType productType = productTypeMapper.toDomain(configurationJpa);

        assertThat(configurationJpa.uuid).isEqualTo(productType.getUUID().toString());
        assertThat(configurationJpa.getProductName()).isEqualTo(productType.getProductName());

    }
}
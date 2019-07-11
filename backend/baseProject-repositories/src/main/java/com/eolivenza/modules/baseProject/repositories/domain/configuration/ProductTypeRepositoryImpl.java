package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.application.repositories.ProductTypeRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public class ProductTypeRepositoryImpl implements ProductTypeRepository {

    private final ProductTypeMapper productTypeMapper;

    private final ProductTypeJpaSpringData productTypeJpaSpringData;

    @Autowired
    public ProductTypeRepositoryImpl(ProductTypeMapper productTypeMapper, ProductTypeJpaSpringData productTypeJpaSpringData) {
        this.productTypeMapper = productTypeMapper;
        this.productTypeJpaSpringData = productTypeJpaSpringData;
    }

    @Override
    public ProductType update(ProductType entity) {
        ProductTypeJpa configurationJpa = productTypeMapper.fromDomain(entity);
        configurationJpa = productTypeJpaSpringData.saveAndFlush(configurationJpa);

        return productTypeMapper.toDomain(configurationJpa);
    }

    @Override
    public ProductType retrieve(UUID identity) {
        ProductType productType = null;

        Optional<ProductTypeJpa> optionalConfigurationJPA =
                productTypeJpaSpringData.findById(ProductType.CONFIGURATION_UUID.toString());

        if (optionalConfigurationJPA.isPresent())
            productType = productTypeMapper.toDomain(optionalConfigurationJPA.get());

        return productType;
    }

    @Override
    public boolean exists(UUID uuid) {
        return productTypeJpaSpringData.existsById(uuid.toString());
    }

    @Override
    public ProductType create(ProductType entity) {
        ProductTypeJpa productTypeJpa = productTypeMapper.fromDomain(entity);
        productTypeJpa = productTypeJpaSpringData.saveAndFlush(productTypeJpa);
        return productTypeMapper.toDomain(productTypeJpa);
    }

    @Override
    public void delete(UUID identity) {
        productTypeJpaSpringData.deleteById(identity.toString());
    }
}

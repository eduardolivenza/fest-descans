package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ProductsRepositoryImpl implements ProductsRepository {

    private final ProductMapper productMapper;
    private final ProductsRepositoryJpaSpringData productsRepositoryJpaSpringData;

    @Autowired
    public ProductsRepositoryImpl(ProductMapper productMapper, ProductsRepositoryJpaSpringData productsRepositoryJpaSpringData) {
        this.productMapper = productMapper;
        this.productsRepositoryJpaSpringData = productsRepositoryJpaSpringData;
    }

    @Override
    public Product update(Product entity) {
        ProductJpa productJpa = productMapper.fromDomain(entity);
        productJpa = productsRepositoryJpaSpringData.saveAndFlush(productJpa);
        return productMapper.toDomain(productJpa);
    }

    @Override
    public Product retrieve(String uuid) {
        Product user = null;
        Optional<ProductJpa> optionalUserJpa =
                productsRepositoryJpaSpringData.findById(uuid);
        if (optionalUserJpa.isPresent())
            user = productMapper.toDomain(optionalUserJpa.get());
        return user;
    }

    @Override
    public List<Product> retrieveAll() {
        List<ProductJpa> productsJpaList = productsRepositoryJpaSpringData.findAll();
        return productsJpaList.stream().map(productMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public boolean existsByProductName(String productName) {
        return productsRepositoryJpaSpringData.existsByProductName(productName);
    }

    @Override
    public boolean existsByuuid(String internalIdentifier){
        return productsRepositoryJpaSpringData.existsById(internalIdentifier);
    }

    @Override
    public Product create(Product entity) {
        try {
            ProductJpa productJpa = productMapper.fromDomain(entity);
            productJpa = productsRepositoryJpaSpringData.saveAndFlush(productJpa);
            return productMapper.toDomain(productJpa);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String identifier) {
        productsRepositoryJpaSpringData.deleteById(identifier);
    }
}

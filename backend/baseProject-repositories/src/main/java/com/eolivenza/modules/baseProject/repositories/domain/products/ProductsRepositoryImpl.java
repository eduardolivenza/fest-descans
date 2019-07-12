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
    public Product retrieve(String email) {
        Product user = null;
        Optional<ProductJpa> optionalUserJpa =
                productsRepositoryJpaSpringData.findById(email);
        if (optionalUserJpa.isPresent())
            user = productMapper.toDomain(optionalUserJpa.get());
        return user;
    }

    @Override
    public List<Product> retrieveAll() {
        List<ProductJpa> usersJpaList = productsRepositoryJpaSpringData.findAll();
        return usersJpaList.stream().map(productMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public boolean exists(String email) {
        return productsRepositoryJpaSpringData.existsById(email);
    }

    @Override
    public Product create(Product entity) {
        try {
            ProductJpa productJpa = productMapper.fromDomain(entity);
            productJpa = productsRepositoryJpaSpringData.saveAndFlush(productJpa);
            return productMapper.toDomain(productJpa);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String email) {
        productsRepositoryJpaSpringData.deleteById(email);
    }
}

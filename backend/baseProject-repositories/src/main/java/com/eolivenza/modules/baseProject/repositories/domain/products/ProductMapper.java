package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;
import com.eolivenza.modules.baseProject.repositories.domain.products.categories.CategoriesMapper;
import com.eolivenza.modules.baseProject.repositories.domain.products.categories.CategoryJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<Product, ProductJpa> {

    AvailableProductSizeMapper availableProductSizeMapper;
    CategoriesMapper categoriesMapper;

    public ProductMapper(AvailableProductSizeMapper availableProductSizeMapper, CategoriesMapper categoriesMapper)
    {
        this.availableProductSizeMapper = availableProductSizeMapper;
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    public Product toDomain(ProductJpa object) {
        Set<AvailableProduct> availableProducts = object.productSizes.stream().map(availableProductSizeMapper::toDomain).collect(Collectors.toSet());
        Category category =  categoriesMapper.toDomain(object.getCategoryJpa());
        return new Product(UUID.fromString(object.getUuid()),category, object.getProductIdentifier(), object.getDescription(), availableProducts );
    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public ProductJpa fromDomain(Product object) {
        HashSet<AvailableProductSizeJpa> sizesList = new HashSet<AvailableProductSizeJpa>();
        CategoryJpa categoryJpa = categoriesMapper.fromDomain(object.getCategory());
        ProductJpa productJpa = new ProductJpa(
                object.getUuid().toString(),
                categoryJpa,
                object.getProductIdentifier(),
                object.getDescription(),
                sizesList
        );
        Set<ProductJpa> productsList = categoryJpa.getProducts();
        productsList.add(productJpa);
        categoryJpa.setProducts(productsList);
        for (AvailableProduct size : object.getAvailableProducts())
        {
            AvailableProductSizeJpa availableProduct = this.availableProductSizeMapper.fromDomain(size);
            availableProduct.productJpa = productJpa;
            sizesList.add(availableProduct);
        }
        productJpa.setProductSizes(sizesList);
        return productJpa;
    }
}

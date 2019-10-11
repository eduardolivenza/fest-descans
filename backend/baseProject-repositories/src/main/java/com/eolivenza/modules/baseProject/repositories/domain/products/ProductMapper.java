package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.products.ProductImage;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import com.eolivenza.modules.baseProject.repositories.domain.products.productImages.ProductImageJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.productImages.ProductImageMapper;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeMapper;
import com.eolivenza.modules.baseProject.repositories.domain.suppliers.SupplierMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<Product, ProductJpa> {

    AvailableProductSizeMapper availableProductSizeMapper;
    ProductImageMapper productImageMapper;
    SupplierMapper supplierMapper;

    public ProductMapper(AvailableProductSizeMapper availableProductSizeMapper, SupplierMapper supplierMapper,  ProductImageMapper productImageMapper)
    {
        this.availableProductSizeMapper = availableProductSizeMapper;
        this.supplierMapper = supplierMapper;
        this.productImageMapper = productImageMapper;
    }

    @Override
    public Product toDomain(ProductJpa object) {
        Set<AvailableProduct> availableProducts = object.getProductSizes().stream().map(availableProductSizeMapper::toDomain).collect(Collectors.toSet());
        Set<ProductImage> productImages = object.getProductImages().stream().map(productImageMapper::toDomain).collect(Collectors.toSet());
        Supplier supplier = supplierMapper.toDomain(object.getSupplier());
        return new Product(UUID.fromString(object.getUuid()),object.getCategoryJpa(), object.getProductName(), object.getDescription(), object.getComfortLevel(), supplier, availableProducts, productImages );
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
        HashSet<ProductImageJpa> imagesList = new HashSet<ProductImageJpa>();
        ProductJpa productJpa = new ProductJpa(
                object.getUuid().toString(),
                object.getCategory(),
                object.getProductName(),
                object.getDescription(),
                object.getComfortLevel(),
                supplierMapper.fromDomain(object.getSupplier()),
                sizesList,
                imagesList
        );
        for (AvailableProduct size : object.getAvailableProducts()) {
            AvailableProductSizeJpa availableProduct = this.availableProductSizeMapper.fromDomain(size);
            availableProduct.productJpa = productJpa;
            sizesList.add(availableProduct);
        }
        for (ProductImage image : object.getProductImages()) {
            ProductImageJpa imageJpa = this.productImageMapper.fromDomain(image);
            imageJpa.productJpa = productJpa;
            imagesList.add(imageJpa);
        }
        productJpa.setProductSizes(sizesList);
        productJpa.setProductImages(imagesList);
        return productJpa;
    }
}

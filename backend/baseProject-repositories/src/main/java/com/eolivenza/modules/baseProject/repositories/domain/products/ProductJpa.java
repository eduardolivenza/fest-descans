package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.repositories.domain.products.productImages.ProductImageJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import com.eolivenza.modules.baseProject.repositories.domain.suppliers.SupplierJpa;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(schema = "baseproject_dm_tables", name = "PRODUCTS")
public class ProductJpa {

    @Id
    public String uuid;

    private Category categoryJpa;

    private String productName;

    private String description;

    private Integer comfortLevel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "supplierJpa",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_SUPPLIER_IDENTIFIER"))
    private SupplierJpa supplier;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "productJpa", fetch = FetchType.EAGER)
    private Set<AvailableProductSizeJpa> productSizes;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "productJpa", fetch = FetchType.EAGER)
    private Set<ProductImageJpa> productImages;

    public ProductJpa() {
        //JPA demands it
    }

    public ProductJpa(String uuid, Category categoryJpa, String productName, String description, Integer comfortLevel, SupplierJpa supplierJpa, Set<AvailableProductSizeJpa> productSizes, Set<ProductImageJpa>imagesList ) {
        this.uuid = uuid;
        this.categoryJpa = categoryJpa;
        this.productName = productName;
        this.description = description;
        this.comfortLevel = comfortLevel;
        this.supplier = supplierJpa;
        this.productSizes = productSizes;
        this.productImages = imagesList;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Category getCategoryJpa() {
        return categoryJpa;
    }

    public void setCategoryJpa(Category categoryJpa) {
        this.categoryJpa = categoryJpa;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AvailableProductSizeJpa> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(Set<AvailableProductSizeJpa> productSizes) {
        this.productSizes = productSizes;
    }

    public Integer getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(Integer comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public SupplierJpa getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierJpa supplier) {
        this.supplier = supplier;
    }

    public Set<ProductImageJpa> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImageJpa> productImages) {
        this.productImages = productImages;
    }
}

package com.eolivenza.modules.baseProject.domain.model.products;

import com.eolivenza.modules.baseProject.domain.model.Entity;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Product extends Entity<Product> {

    private String productName;
    @NotNull
    private UUID uuid;

    private Category category;

    private String description;

    private  Integer comfortLevel;

    private Supplier supplier;

    @NotNull
    private Set<@NotBlank AvailableProduct> availableProducts;

    @NotNull
    private Set<@NotBlank ProductImage> productImages;

    public Product(Category category, String productName, String description,  Integer comfortLevel, Supplier supplier, HashSet<AvailableProduct> availableProducts, Set<ProductImage> productImages){
        this(UUID.randomUUID(), category,  productName, description, comfortLevel, supplier, availableProducts, productImages);
    }

    public Product(UUID uuid,  Category category, String productName, String description,  Integer comfortLevel, Supplier supplier, Set<AvailableProduct> availableProducts, Set<ProductImage> productImages){
       this.uuid = uuid;
       this.category = category;
       this.productName = productName;
       this.description = description;
       this.comfortLevel = comfortLevel;
       this.supplier = supplier;
       this.availableProducts = availableProducts;
       this.productImages = productImages;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Product setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Set<AvailableProduct> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(Set<AvailableProduct> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(Integer comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void addAvailableSize(AvailableProduct availableProduct) {
        this.availableProducts.add(availableProduct);
    }

    public void addProductImage(ProductImage productImage) {
        this.productImages.add(productImage);
    }

    @Override
    public int hashCodeCalculation() {
        return super.hashCode();
    }

    @Override
    public boolean hasSameIdentity(Product other) {
        return super.equals(other) && hasSameIdentity((Product) other);
    }
}

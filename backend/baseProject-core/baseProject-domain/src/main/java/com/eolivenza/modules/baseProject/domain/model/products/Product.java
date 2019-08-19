package com.eolivenza.modules.baseProject.domain.model.products;

import com.eolivenza.modules.baseProject.domain.model.Entity;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Product extends Entity<Product> {

    @NotNull
    private UUID uuid;

    private Category category;

    @NotBlank
    private String productIdentifier;

    private String description;

    private  Integer comfortLevel;

    private Supplier supplier;

    @NotNull
    private Set<@NotBlank AvailableProduct> availableProducts;

    public Product(Category category, String productIdentifier, String description,  Integer comfortLevel, Supplier supplier, HashSet<AvailableProduct> availableProducts){
        this(UUID.randomUUID(), category, productIdentifier, description, comfortLevel, supplier, availableProducts);
    }

    public Product(UUID uuid,  Category category, String productIdentifier,String description,  Integer comfortLevel, Supplier supplier, Set<AvailableProduct> availableProducts){
       this.uuid = uuid;
       this.category = category;
       this.productIdentifier= productIdentifier;
       this.description = description;
       this.comfortLevel = comfortLevel;
       this.supplier = supplier;
       this.availableProducts = availableProducts;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Product setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public Product setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
        return this;
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

    @Override
    public int hashCodeCalculation() {
        return super.hashCode();
    }

    @Override
    public boolean hasSameIdentity(Product other) {
        return super.equals(other) && hasSameIdentity((Product) other);
    }

    public void addAvailableSize(AvailableProduct availableProduct) {
        this.availableProducts.add(availableProduct);
    }
}

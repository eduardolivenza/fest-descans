package com.eolivenza.modules.baseProject.domain.model.products;

import com.eolivenza.modules.baseProject.domain.model.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Product extends Entity<Product> {

    @NotNull
    private UUID uuid;

    @NotBlank
    private String productIdentifier;

    @NotNull
    private Set<@NotBlank AvailableProduct> availableProducts;

    public Product(String productIdentifier, HashSet<AvailableProduct> availableProducts){
        this(UUID.randomUUID(), productIdentifier, new HashSet<>());
    }

    public Product(UUID uuid, String productIdentifier, HashSet<AvailableProduct> availableProducts){
       this.uuid = uuid;
       this.productIdentifier= productIdentifier;
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

    public void overwriteWith(Product otherProduct) {
        setUuid(otherProduct.getUuid());
        setProductIdentifier(otherProduct.getProductIdentifier());
    }

    @Override
    public int hashCodeCalculation() {
        return 0;
    }

    @Override
    public boolean hasSameIdentity(Product other) {
        return false;
    }
}

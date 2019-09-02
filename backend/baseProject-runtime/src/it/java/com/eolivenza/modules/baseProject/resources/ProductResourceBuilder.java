package com.eolivenza.modules.baseProject.resources;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.*;
import java.util.HashSet;
import java.util.Set;


public final class ProductResourceBuilder {

    private String description;
    private String category;
    private String productIdentifier;
    private String productName;
    private Integer comfortLevel;
    private SupplierResource supplier;
    private Set<AvailableSizeResource> sizes;

    private ProductResourceBuilder() {
    }

    public static ProductResourceBuilder aProductResource() {
        return new ProductResourceBuilder();
    }

    public ProductResourceBuilder withIdentifier(String identifier) {
        this.productIdentifier = identifier;
        return this;
    }

    public ProductResourceBuilder withName(String name) {
        this.productName = name;
        return this;
    }

    public ProductResourceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductResourceBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductResourceBuilder withComfortLevel(Integer comfortLevel) {
        this.comfortLevel = comfortLevel;
        return this;
    }

    public ProductResourceBuilder withSupplier(SupplierResource supplier) {
        this.supplier = supplier;
        return this;
    }

    public ProductResource build() {
        return new ProductResource(category, productIdentifier, productName, description, comfortLevel, supplier, new HashSet<>());
    }

}

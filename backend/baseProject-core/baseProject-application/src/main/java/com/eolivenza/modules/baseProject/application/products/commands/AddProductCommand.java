package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;

import java.util.Set;

public class AddProductCommand {


    public String productIdentifier;

    public String category;

    public String productDescription;

    public Set<AvailableProduct> sizes;

    public AddProductCommand(String productIdentifier, String category, String productDescription, Set<AvailableProduct> sizesSet) {
        this.productIdentifier = productIdentifier;
        this.productDescription = productDescription;
        this.category = category;
        this.sizes = sizesSet;

    }
}

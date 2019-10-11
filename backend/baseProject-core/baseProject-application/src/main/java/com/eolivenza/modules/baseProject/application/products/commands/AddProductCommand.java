package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;

import java.util.Set;

public class AddProductCommand {

    public String productName;

    public String category;

    public String productDescription;

    public Integer comfortLevel;

    public Supplier supplier;

    public Set<AvailableProduct> sizes;

    public AddProductCommand( String productName, String category, String productDescription, Integer comfortLevel, Supplier supplier, Set<AvailableProduct> sizesSet) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.comfortLevel = comfortLevel;
        this.supplier = supplier;
        this.sizes = sizesSet;
    }
}

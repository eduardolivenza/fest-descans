package com.eolivenza.modules.baseProject.application.products.queries;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productIdentifier) {
        super("Product not found: " + productIdentifier);
    }
}

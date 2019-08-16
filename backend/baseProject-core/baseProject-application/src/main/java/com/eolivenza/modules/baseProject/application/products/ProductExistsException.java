package com.eolivenza.modules.baseProject.application.products;

public class ProductExistsException extends RuntimeException {
    public ProductExistsException(String identifier) {
        super("Product with ID: ".concat(identifier).concat("already exists"));
    }
}

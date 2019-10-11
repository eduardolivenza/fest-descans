package com.eolivenza.modules.baseProject.application.products;

public class ProductWithThisNameExistsException extends RuntimeException {
    public ProductWithThisNameExistsException(String name) {
        super("Product with name: ".concat(name).concat(" already exists"));
    }
}

package com.eolivenza.modules.baseProject.domain.model.products;

public class ProductImage {

    private String identifier;
    private String filename;

    public ProductImage(String identifier, String filename) {
        this.identifier = identifier;
        this.filename = filename;
    }

    public ProductImage( String filename) {
        this.filename = filename;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getFilename() {
        return filename;
    }


}

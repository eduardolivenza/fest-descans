package com.eolivenza.modules.baseProject.application.products.commands.productImages;

import java.io.InputStream;

public class AddProductImageCommand {

    private String productIdentifier;
    private String filename;
    private InputStream fileContent;

    public AddProductImageCommand(String productIdentifier, String filename, InputStream fileContent) {
        this.productIdentifier= productIdentifier;
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public String getFilename() {
        return filename;
    }

    public InputStream getFileContent() {
        return fileContent;
    }
}

package com.eolivenza.modules.baseProject.application.images.commands;

import java.io.InputStream;

public class StoreImageCommand {

    private String productIdentifier;
    private String filename;
    private InputStream fileContent;

    public StoreImageCommand(String productIdentifier, String filename, InputStream fileContent) {
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

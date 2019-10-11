package com.eolivenza.modules.baseProject.application.products.commands.productImages;

import java.io.InputStream;

public class AddProductImageCommand {

    private String getIdentifier;
    private String filename;
    private InputStream fileContent;

    public AddProductImageCommand(String getIdentifier, String filename, InputStream fileContent) {
        this.getIdentifier= getIdentifier;
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public String getIdentifier() {
        return getIdentifier;
    }

    public String getFilename() {
        return filename;
    }

    public InputStream getFileContent() {
        return fileContent;
    }
}
